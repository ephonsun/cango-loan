package com.cangoframework.accounting.core.pmt;


public class Schedule {
	private double executeYearRate;//当前期次
	private double executeMouthRate;//当前期次
	private int loanPeriod;//贷款期次
	private int curentPeriod;//当前期次
	private double repaymentPrincipal;//还款本金
	private double repaymentInterest;//还款利息
	private double repaymentAmount;//还款金额 = 还款本金 +　还款利息
	private double previousResiduePrincipal;//上期剩余本金
	private double currentResiduePrincipal;//当前剩余本金
	private String repaymentDate;//还款日期
	private int firstRepaymentDay;//首次还款日
	
	public Schedule() {
	}
	public Schedule(Schedule previousSchedule) {
		setExecuteYearRate(previousSchedule.getExecuteYearRate());
		setExecuteMouthRate(previousSchedule.getExecuteMouthRate());
		setPreviousResiduePrincipal(previousSchedule.getCurrentResiduePrincipal());
		setCurentPeriod(previousSchedule.getCurentPeriod()+1);
		setLoanPeriod(previousSchedule.getLoanPeriod());
	}
	public int getCurentPeriod() {
		return curentPeriod;
	}
	public void setCurentPeriod(int curentPeriod) {
		this.curentPeriod = curentPeriod;
	}
	public double getRepaymentPrincipal() {
		return repaymentPrincipal;
	}
	public void setRepaymentPrincipal(double repaymentPrincipal) {
		this.repaymentPrincipal = repaymentPrincipal;
	}
	public double getRepaymentInterest() {
		return repaymentInterest;
	}
	public void setRepaymentInterest(double repaymentInterest) {
		this.repaymentInterest = repaymentInterest;
	}
	public double getRepaymentAmount() {
		return repaymentAmount;
	}
	public void setRepaymentAmount(double repaymentAmount) {
		this.repaymentAmount = repaymentAmount;
	}
	public double getPreviousResiduePrincipal() {
		return previousResiduePrincipal;
	}
	public void setPreviousResiduePrincipal(double previousResiduePrincipal) {
		this.previousResiduePrincipal = previousResiduePrincipal;
	}
	public double getCurrentResiduePrincipal() {
		return currentResiduePrincipal;
	}
	public void setCurrentResiduePrincipal(double currentResiduePrincipal) {
		this.currentResiduePrincipal = currentResiduePrincipal;
	}
	public double getExecuteYearRate() {
		return executeYearRate;
	}
	public void setExecuteYearRate(double executeYearRate) {
		this.executeYearRate = executeYearRate;
	}
	public double getExecuteMouthRate() {
		return executeMouthRate;
	}
	public void setExecuteMouthRate(double executeMouthRate) {
		this.executeMouthRate = executeMouthRate;
	}
	public int getLoanPeriod() {
		return loanPeriod;
	}
	public void setLoanPeriod(int loanPeriod) {
		this.loanPeriod = loanPeriod;
	}
	public int getFirstRepaymentDay() {
		return firstRepaymentDay;
	}
	public void setFirstRepaymentDay(int firstRepaymentDay) {
		this.firstRepaymentDay = firstRepaymentDay;
	}
	public String getRepaymentDate() {
		return repaymentDate;
	}
	public void setRepaymentDate(String repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	@Override
	public String toString() {
		return "还款计划 [期次=" + curentPeriod
				+ ", 还款本金=" + repaymentPrincipal
				+ ", 还款利息=" + repaymentInterest
				+ ", 还款金额=" + repaymentAmount
				+ ", 上次剩余本金=" + previousResiduePrincipal
				+ ", 当前剩余本金=" + currentResiduePrincipal
				+ ", 还款日期=" + repaymentDate + "]";
	}
	
	
	
}
