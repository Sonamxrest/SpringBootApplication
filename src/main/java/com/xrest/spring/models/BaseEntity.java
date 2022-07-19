package com.xrest.spring.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Version;
import java.awt.desktop.ScreenSleepEvent;
import java.io.Serializable;
import java.util.Date;

@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity<T extends Serializable> extends AbstractPersistable<T> {
    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt = new Date();

    @Version
    int version;

    @Override
    public void setId(T id) {
        super.setId(id);
    }

}
