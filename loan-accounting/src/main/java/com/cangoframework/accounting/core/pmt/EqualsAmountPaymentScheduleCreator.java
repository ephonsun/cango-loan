package com.cangoframework.accounting.core.pmt;

import java.util.List;

import com.cangoframework.accounting.utils.Arith;

/**
 * 等额本息还款计算逻辑
 * @author Administrator
 *
 */
public class EqualsAmountPaymentScheduleCreator extends PaymentScheduleCreator {

	public EqualsAmountPaymentScheduleCreator() {
		super();
	}

	public EqualsAmountPaymentScheduleCreator(double loanAmount, int loanPeriod,
			double executeYearRate) {
		super(loanAmount, loanPeriod, executeYearRate);
	}

	public EqualsAmountPaymentScheduleCreator(double loanAmount, int loanPeriod,
			double executeYearRate, int precision) {
		super(loanAmount, loanPeriod, executeYearRate, precision);
	}
	
	private double currentPeriodRepaymentAmount = 0.0;
	@Override
	public Schedule getScheduleObject(Schedule previousSchedule) {
		Schedule schedule = new Schedule(previousSchedule);
		//计算月利率
		double executeMouthRate = schedule.getExecuteMouthRate();
		//当前期数
		int currentPeriod = schedule.getCurentPeriod();
		//剩余期次
		int residuePeriod = schedule.getLoanPeriod()+1-schedule.getCurentPeriod();
		//上期剩余本金
		double previousResiduePrincipal = schedule.getPreviousResiduePrincipal();
		
		if(currentPeriod==1){
			//还款金额
			currentPeriodRepaymentAmount = Arith.round(
					(previousResiduePrincipal * executeMouthRate) 
					* java.lang.Math.pow((1+executeMouthRate),residuePeriod)
					/ (java.lang.Math.pow((1+executeMouthRate),residuePeriod)-1),super.getPrecision());
		}
		if(residuePeriod==1){
			double lastRepaymentPrincipal = previousResiduePrincipal;
			double lastCurrentPeriodRepaymentInterest = Arith.round(previousResiduePrincipal*executeMouthRate, super.getPrecision());
			currentPeriodRepaymentAmount = Arith.add(lastRepaymentPrincipal, lastCurrentPeriodRepaymentInterest);
		}
		
		//还款利息
		double currentPeriodRepaymentInterest = Arith.round(previousResiduePrincipal*executeMouthRate, super.getPrecision());
		//还款本金
		double currentPeriodRepaymentPrincipal = Arith.sub(currentPeriodRepaymentAmount, currentPeriodRepaymentInterest);
		//计算下一期的还款日期
		//如果首次还款日为当月月底1.31 2.28 , 3.31, 4.30
		
		String nextRepaymentDate = getNextRepaymentDate(previousSchedule.getRepaymentDate(),previousSchedule.getFirstRepaymentDay());
		schedule.setRepaymentDate(nextRepaymentDate);
		schedule.setFirstRepaymentDay(previousSchedule.getFirstRepaymentDay());
		
		schedule.setRepaymentAmount(currentPeriodRepaymentAmount);
		schedule.setRepaymentInterest(currentPeriodRepaymentInterest);
		schedule.setRepaymentPrincipal(currentPeriodRepaymentPrincipal);
		schedule.setCurrentResiduePrincipal(Arith.sub(schedule.getPreviousResiduePrincipal(), currentPeriodRepaymentPrincipal));
		
		return schedule;
	}
	
	public static void main(String[] args) {
		
		EqualsAmountPaymentScheduleCreator creator = new EqualsAmountPaymentScheduleCreator();
		creator.setExecuteYearRate(6.00);
		creator.setPutoutDate("2017-01-29");
		creator.setLoanPeriod(13);
		creator.setLoanAmount(7000);
		List<Schedule> scheduleList = creator.getScheduleList();
		creator.showRepaymentSchedule(scheduleList);
	}

}
