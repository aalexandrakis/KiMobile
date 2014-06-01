/**
 * User.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.aalexandrakis.kimobile.pojos;

import com.aalexandrakis.kimobile.pojos.User;

public class User  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.math.BigInteger userCoins;

    private java.lang.String userEmail;

    private java.math.BigInteger userId;

    private java.lang.Integer userLevel;

    private java.lang.String userName;

    private java.lang.String userPassword;

    public User() {
    }

    public User(
           java.math.BigInteger userCoins,
           java.lang.String userEmail,
           java.math.BigInteger userId,
           java.lang.Integer userLevel,
           java.lang.String userName,
           java.lang.String userPassword) {
           this.userCoins = userCoins;
           this.userEmail = userEmail;
           this.userId = userId;
           this.userLevel = userLevel;
           this.userName = userName;
           this.userPassword = userPassword;
    }


    /**
     * Gets the userCoins value for this User.
     * 
     * @return userCoins
     */
    public java.math.BigInteger getUserCoins() {
        return userCoins;
    }


    /**
     * Sets the userCoins value for this User.
     * 
     * @param userCoins
     */
    public void setUserCoins(java.math.BigInteger userCoins) {
        this.userCoins = userCoins;
    }


    /**
     * Gets the userEmail value for this User.
     * 
     * @return userEmail
     */
    public java.lang.String getUserEmail() {
        return userEmail;
    }


    /**
     * Sets the userEmail value for this User.
     * 
     * @param userEmail
     */
    public void setUserEmail(java.lang.String userEmail) {
        this.userEmail = userEmail;
    }


    /**
     * Gets the userId value for this User.
     * 
     * @return userId
     */
    public java.math.BigInteger getUserId() {
        return userId;
    }


    /**
     * Sets the userId value for this User.
     * 
     * @param userId
     */
    public void setUserId(java.math.BigInteger userId) {
        this.userId = userId;
    }


    /**
     * Gets the userLevel value for this User.
     * 
     * @return userLevel
     */
    public java.lang.Integer getUserLevel() {
        return userLevel;
    }


    /**
     * Sets the userLevel value for this User.
     * 
     * @param userLevel
     */
    public void setUserLevel(java.lang.Integer userLevel) {
        this.userLevel = userLevel;
    }


    /**
     * Gets the userName value for this User.
     * 
     * @return userName
     */
    public java.lang.String getUserName() {
        return userName;
    }


    /**
     * Sets the userName value for this User.
     * 
     * @param userName
     */
    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }


    /**
     * Gets the userPassword value for this User.
     * 
     * @return userPassword
     */
    public java.lang.String getUserPassword() {
        return userPassword;
    }


    /**
     * Sets the userPassword value for this User.
     * 
     * @param userPassword
     */
    public void setUserPassword(java.lang.String userPassword) {
        this.userPassword = userPassword;
    }

    
}
