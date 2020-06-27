package com.eg.yafi.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"app_user_id", "thread_id"}))
public class AppUserThreadLikeRel extends BaseEntity{
    @NotNull
    @ManyToOne
    private AppUser appUser;

    @NotNull
    @ManyToOne
    private Thread thread;

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }
}
