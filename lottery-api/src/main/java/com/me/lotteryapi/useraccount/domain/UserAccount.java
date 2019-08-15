package com.me.lotteryapi.useraccount.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 用户账号
 * 
 * @author zohar
 * @date 2018年12月26日
 *
 */
@Getter
@Setter
public class UserAccount {

	/**
	 * 主键id
	 */
	private String id;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 账号类型
	 */
	private String accountType;

	/**
	 * 账号级别
	 */
	private Integer accountLevel;

	/**
	 * 账号级别路径
	 */
	private String accountLevelPath;

	/**
	 * 返点
	 */
	private Double rebate;

	/**
	 * 赔率
	 */
	private Double odds;

	/**
	 * 登录密码
	 */
	private String loginPwd;

	/**
	 * 资金密码
	 */
	private String moneyPwd;

	/**
	 * 余额
	 */
	private Double balance;

	/**
	 * 状态
	 */
	private String state;

	/**
	 * 注册时间
	 */
	private Date registeredTime;

	/**
	 * 最近登录时间
	 */
	private Date latelyLoginTime;

	/**
	 * 开户银行
	 */
	private String openAccountBank;

	/**
	 * 开户人姓名
	 */
	private String accountHolder;

	/**
	 * 银行卡账号
	 */
	private String bankCardAccount;

	/**
	 * 银行资料最近修改时间
	 */
	private Date bankInfoLatelyModifyTime;

	/**
	 * 邀请人id
	 */
	private String inviterId;

	/**
	 * 邀请人
	 */
	private UserAccount inviter;

	/**
	 * 乐观锁版本号
	 */

	private Long version;


}
