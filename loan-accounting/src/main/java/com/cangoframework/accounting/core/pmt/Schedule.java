package com.cangoframework.accounting.core.pmt;


public class Schedule {
	private double executeYearRate;//��ǰ�ڴ�
	private double executeMouthRate;//��ǰ�ڴ�
	private int loanPeriod;//�����ڴ�
	private int curentPeriod;//��ǰ�ڴ�
	private double repaymentPrincipal;//�����
	private double repaymentInterest;//������Ϣ
	private double repaymentAmount;//������ = ����� +��������Ϣ
	private double previousResiduePrincipal;//����ʣ�౾��
	private double currentResiduePrincipal;//��ǰʣ�౾��
	private String repaymentDate;//��������
	private int firstRepaymentDay;//�״λ�����
	
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
		return "����ƻ� [�ڴ�=" + curentPeriod
				+ ", �����=" + repaymentPrincipal
				+ ", ������Ϣ=" + repaymentInterest
				+ ", ������=" + repaymentAmount
				+ ", �ϴ�ʣ�౾��=" + previousResiduePrincipal
				+ ", ��ǰʣ�౾��=" + currentResiduePrincipal
				+ ", ��������=" + repaymentDate + "]";
	}
	
	
	
}
