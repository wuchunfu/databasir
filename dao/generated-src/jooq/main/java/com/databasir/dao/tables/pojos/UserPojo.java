/*
 * This file is generated by jOOQ.
 */
package com.databasir.dao.tables.pojos;


import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer       id;
    private String        email;
    private String        password;
    private String        nickname;
    private String        avatar;
    private Boolean       enabled;
    private Boolean       deleted;
    private LocalDateTime updateAt;
    private LocalDateTime createAt;

    public UserPojo() {}

    public UserPojo(UserPojo value) {
        this.id = value.id;
        this.email = value.email;
        this.password = value.password;
        this.nickname = value.nickname;
        this.avatar = value.avatar;
        this.enabled = value.enabled;
        this.deleted = value.deleted;
        this.updateAt = value.updateAt;
        this.createAt = value.createAt;
    }

    public UserPojo(
        Integer       id,
        String        email,
        String        password,
        String        nickname,
        String        avatar,
        Boolean       enabled,
        Boolean       deleted,
        LocalDateTime updateAt,
        LocalDateTime createAt
    ) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.avatar = avatar;
        this.enabled = enabled;
        this.deleted = deleted;
        this.updateAt = updateAt;
        this.createAt = createAt;
    }

    /**
     * Getter for <code>databasir.user.id</code>.
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Setter for <code>databasir.user.id</code>.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter for <code>databasir.user.email</code>.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Setter for <code>databasir.user.email</code>.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for <code>databasir.user.password</code>.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Setter for <code>databasir.user.password</code>.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for <code>databasir.user.nickname</code>.
     */
    public String getNickname() {
        return this.nickname;
    }

    /**
     * Setter for <code>databasir.user.nickname</code>.
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Getter for <code>databasir.user.avatar</code>.
     */
    public String getAvatar() {
        return this.avatar;
    }

    /**
     * Setter for <code>databasir.user.avatar</code>.
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * Getter for <code>databasir.user.enabled</code>.
     */
    public Boolean getEnabled() {
        return this.enabled;
    }

    /**
     * Setter for <code>databasir.user.enabled</code>.
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Getter for <code>databasir.user.deleted</code>.
     */
    public Boolean getDeleted() {
        return this.deleted;
    }

    /**
     * Setter for <code>databasir.user.deleted</code>.
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * Getter for <code>databasir.user.update_at</code>.
     */
    public LocalDateTime getUpdateAt() {
        return this.updateAt;
    }

    /**
     * Setter for <code>databasir.user.update_at</code>.
     */
    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    /**
     * Getter for <code>databasir.user.create_at</code>.
     */
    public LocalDateTime getCreateAt() {
        return this.createAt;
    }

    /**
     * Setter for <code>databasir.user.create_at</code>.
     */
    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UserPojo (");

        sb.append(id);
        sb.append(", ").append(email);
        sb.append(", ").append(password);
        sb.append(", ").append(nickname);
        sb.append(", ").append(avatar);
        sb.append(", ").append(enabled);
        sb.append(", ").append(deleted);
        sb.append(", ").append(updateAt);
        sb.append(", ").append(createAt);

        sb.append(")");
        return sb.toString();
    }
}
