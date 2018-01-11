package com.cangoframework.accounting.core.pmt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cangoframework.accounting.utils.Arith;
import com.cangoframework.accounting.utils.DateUtils;

public abstract class PaymentScheduleCreator {
	private double loanAmount;//贷款金额
	private int loanPeriod;//贷款周期（月）
	private double executeYearRate;//执行年利率
	private int precision;
	private String putoutDate; 
	
	public PaymentScheduleCreator() {
		initDefault();
	}

	public PaymentScheduleCreator(double loanAmount, int loanPeriod,
			double executeYearRate) {
		this();
		this.loanAmount = loanAmount;
		this.loanPeriod = loanPeriod;
		this.executeYearRate = executeYearRate;
	}

	public PaymentScheduleCreator(double loanAmount, int loanPeriod,
			double executeYearRate, int precision) {
		this();
		this.loanAmount = loanAmount;
		this.loanPeriod = loanPeriod;
		this.executeYearRate = executeYearRate;
		this.precision = precision;
	}
	
	private void initDefault(){
		Date nowDate = new Date();
		setPrecision(2);
		setPutoutDate(DateUtils.getDate(nowDate, DateUtils.DATE_FORMAT_DEFAULT));
	}
	
	public List<Schedule> getScheduleList(){
		return getScheduleList(this.loanAmount,this.executeYearRate,this.loanPeriod,this.putoutDate);
	}
	
	public List<Schedule> getScheduleList(double loanAmount,double executeYearRate,int loanPeriod,String sFirstRepaymentDate){
		List<Schedule> scheduleList = new ArrayList<Schedule>();
		Schedule nextSchedule = this.getZeroScheduleObject(loanAmount,executeYearRate,loanPeriod,sFirstRepaymentDate);
		
		for (int i = 1; i <= loanPeriod; i++) {
			nextSchedule = this.getScheduleObject(nextSchedule);
			scheduleList.add(nextSchedule);
		}
		return scheduleList;
	}

	private Schedule getZeroScheduleObject(double loanAmount,double executeYearRate,int loanPeriod,String putoutDate) {
		Schedule schedule = new Schedule();
		schedule.setLoanPeriod(loanPeriod);
		schedule.setExecuteYearRate(executeYearRate);
		schedule.setExecuteMouthRate(Arith.div(executeYearRate, 1200));
		schedule.setCurentPeriod(0);
		schedule.setPreviousResiduePrincipal(loanAmount);
		schedule.setCurrentResiduePrincipal(loanAmount);
		schedule.setRepaymentAmount(0);
		schedule.setRepaymentInterest(0);
		schedule.setRepaymentPrincipal(0);
		schedule.setRepaymentDate(putoutDate);
		schedule.setFirstRepaymentDay(DateUtils.getDay(putoutDate));
		return schedule;
	}
	
	public String getNextRepaymentDate(String repaymentDate,int firstRepaymentDay) {
		String nextRepaymentDate = null;
		String tempDate = null;
		switch (firstRepaymentDay) {
		case 31:
			//获取下一个月的月底
			nextRepaymentDate = DateUtils.getMonthEnd(DateUtils.getRelativeDefaultDate(repaymentDate, 0, 1, 0 ));
			break;
		case 30:
			//先加上一个月
			tempDate = DateUtils.getRelativeDefaultDate(repaymentDate, 0, 1, 0 );
			//判断是否是月底
			if(DateUtils.isMonthEnd(tempDate)){
				nextRepaymentDate = tempDate ;
			}else{
				nextRepaymentDate = DateUtils.getRelativeDefaultDate(tempDate, 0, 0, 30-DateUtils.getDay(tempDate));
			}
			break;
		case 29:
			//先加上一个月
			tempDate = DateUtils.getRelativeDefaultDate(repaymentDate, 0, 1, 0 );
			//判断是否是月底
			if(DateUtils.isMonthEnd(tempDate)){
				nextRepaymentDate = tempDate ;
			}else{
				nextRepaymentDate = DateUtils.getRelativeDefaultDate(tempDate, 0, 0, 29-DateUtils.getDay(tempDate));
			}
			break;
		default:
			nextRepaymentDate = DateUtils.getRelativeDefaultDate(repaymentDate, 0, 1, 0 );
			break;
		}
		return nextRepaymentDate;
	}
	
	public abstract Schedule getScheduleObject(Schedule previousSchedule);
	
	public void showRepaymentSchedule(List<Schedule> scheduleList){
		for (Schedule schedule : scheduleList) {
			System.out.println(schedule);
		}
	}
	
	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public int getLoanPeriod() {
		return loanPeriod;
	}

	public void setLoanPeriod(int loanPeriod) {
		this.loanPeriod = loanPeriod;
	}

	public double getExecuteYearRate() {
		return executeYearRate;
	}

	public void setExecuteYearRate(double executeYearRate) {
		this.executeYearRate = executeYearRate;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public String getPutoutDate() {
		return putoutDate;
	}

	public void setPutoutDate(String putoutDate) {
		this.putoutDate = putoutDate;
	}

}
