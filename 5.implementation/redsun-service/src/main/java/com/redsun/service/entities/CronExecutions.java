package com.redsun.service.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * CronExecutions entity
 */
@Component
@Scope("prototype")
public class CronExecutions extends AbstractEntity {

    // cron
    private String cron;
    // file_executed
    private String fileExecuted;
    // idle
    private Integer idle;
    // fonction_name
    private String fonctionName;
    // next_time
    private String nextTime;
    // client_id
    private Integer clientId;
	// totalCount
	private Integer totalCount;


	// cron
    public void setCron(final String cron) {
        this.cron = cron;
    }
    public String getCron() {
        return this.cron;
    }

	// fileExecuted
    public void setFileExecuted(final String fileExecuted) {
        this.fileExecuted = fileExecuted;
    }
    public String getFileExecuted() {
        return this.fileExecuted;
    }

	// idle
    public void setIdle(final Integer idle) {
        this.idle = idle;
    }
    public Integer getIdle() {
        return this.idle;
    }

	// fonctionName
    public void setFonctionName(final String fonctionName) {
        this.fonctionName = fonctionName;
    }
    public String getFonctionName() {
        return this.fonctionName;
    }

	// nextTime
    public void setNextTime(final String nextTime) {
        this.nextTime = nextTime;
    }
    public String getNextTime() {
        return this.nextTime;
    }

	// clientId
    public void setClientId(final Integer clientId) {
        this.clientId = clientId;
    }
    public Integer getClientId() {
        return this.clientId;
    }

	// totalCount
    public Integer getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(final Integer totalCount) {
        this.totalCount = totalCount;
    }

	// toString
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(cron);
        sb.append("|");
        sb.append(fileExecuted);
        sb.append("|");
        sb.append(idle);
        sb.append("|");
        sb.append(fonctionName);
        sb.append("|");
        sb.append(nextTime);
        sb.append("|");
        sb.append(clientId);
        return sb.toString(); 
    } 

}
