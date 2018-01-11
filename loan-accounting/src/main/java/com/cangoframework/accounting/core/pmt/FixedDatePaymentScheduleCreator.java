package com.cangoframework.accounting.core.pmt;

import java.util.Date;
import java.util.List;

import com.cangoframework.accounting.utils.Arith;
import com.cangoframework.accounting.utils.DateUtils;

/**
 * 等额本息方式的固定还款日还款计算逻辑
 * @author Administrator
 *
 */
public class FixedDatePaymentScheduleCreator extends EqualsAmountPaymentScheduleCreator{
	
	private int fixedRepaymentDay;//固定还款日
	
	public FixedDatePaymentScheduleCreator() {
		super();
		setFixedRepaymentDay(DateUtils.getDay(new Date()));
	}

	public FixedDatePaymentScheduleCreator(double loanAmount, int loanPeriod,
			double executeYearRate, int fixedRepaymentDay,int precision) {
		super(loanAmount, loanPeriod, executeYearRate, precision);
		setFixedRepaymentDay(fixedRepaymentDay);
	}

	public FixedDatePaymentScheduleCreator(double loanAmount, int loanPeriod,
			double executeYearRate,int fixedRepaymentDay) {
		super(loanAmount, loanPeriod, executeYearRate);
		setFixedRepaymentDay(fixedRepaymentDay);
	}

	@Override
	public List<Schedule> getScheduleList(double loanAmount,
			double executeYearRate, int loanPeriod,String sPutoutDate) {
		List<Schedule> scheduleList = getScheduleList(loanAmount, executeYearRate, loanPeriod, sPutoutDate,fixedRepaymentDay);
		return scheduleList;
	}
	
	public List<Schedule> getScheduleList(double loanAmount,
			double executeYearRate, int loanPeriod,String sPutoutDate,int fixedRepaymentDay) {
		
		int daynterval = fixedRepaymentDay - DateUtils.getDay(sPutoutDate);
		
		String relativePutoutDate = DateUtils.getRelativeDefaultDate(sPutoutDate, 0, 0, daynterval);
		
		List<Schedule> scheduleList = super.getScheduleList(loanAmount, executeYearRate, loanPeriod,relativePutoutDate);
		
		Schedule firstSchedule = scheduleList.get(0);
		
		double repaymentInterest = firstSchedule.getRepaymentInterest();
		double repaymentAmount = firstSchedule.getRepaymentAmount();
		
		//计算利息差距
		double money = Arith.round((firstSchedule.getExecuteYearRate()/100/360)*firstSchedule.getPreviousResiduePrincipal()*daynterval,2);
		firstSchedule.setRepaymentInterest(Arith.add(repaymentInterest, money));
		firstSchedule.setRepaymentAmount(Arith.add(repaymentAmount, money));
		
		//计算最后一期的日期差距 
		Schedule lastSchedule = scheduleList.get(scheduleList.size()-1);
		repaymentInterest = lastSchedule.getRepaymentInterest();
		repaymentAmount = lastSchedule.getRepaymentAmount();
		
		money = Arith.round(lastSchedule.getExecuteYearRate()/100/360*lastSchedule.getPreviousResiduePrincipal()*(-daynterval),2);
		lastSchedule.setRepaymentInterest(Arith.add(repaymentInterest, money));
		lastSchedule.setRepaymentAmount(Arith.add(repaymentAmount, money));
		lastSchedule.setRepaymentDate(DateUtils.getRelativeDefaultDate(lastSchedule.getRepaymentDate(), 0, 0, (-daynterval)));
		
		return scheduleList;
	}

	public int getFixedRepaymentDay() {
		return fixedRepaymentDay;
	}

	public void setFixedRepaymentDay(int fixedRepaymentDay) {
		this.fixedRepaymentDay = fixedRepaymentDay;
	}
	
	public static void main(String[] args) {
		
		FixedDatePaymentScheduleCreator creator = new FixedDatePaymentScheduleCreator();
		creator.setExecuteYearRate(6.00);
		creator.setPutoutDate("2017-01-29");
		creator.setFixedRepaymentDay(20);
		creator.setLoanPeriod(12);
		creator.setLoanAmount(7000);
		List<Schedule> scheduleList = creator.getScheduleList();
		creator.showRepaymentSchedule(scheduleList);
		
	}
	
}
