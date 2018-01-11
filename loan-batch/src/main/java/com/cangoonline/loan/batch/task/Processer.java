package com.cangoonline.loan.batch.task;

import com.cangoonline.loan.batch.entity.TaskPool;

import java.sql.Connection;

public interface Processer {
	boolean process(TaskPool task, Connection conn);
}
