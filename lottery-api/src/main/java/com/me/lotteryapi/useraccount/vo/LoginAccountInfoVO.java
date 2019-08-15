package com.me.lotteryapi.useraccount.vo;

import com.me.lotteryapi.dictconfig.DictHolder;
import com.me.lotteryapi.useraccount.domain.UserAccount;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * 登录账号信息,包含密码
 * 
 * @author zohar
 * @date 2019年1月25日
 *
 */
@Data
public class LoginAccountInfoVO {

	/**
	 * 主键id
	 */
	private String id;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 登录密码
	 */
	private String loginPwd;

	/**
	 * 账号类型
	 */
	private String accountType;

	private String accountTypeName;

	public static LoginAccountInfoVO convertFor(UserAccount userAccount) {
		if (userAccount == null) {
			return null;
		}
		LoginAccountInfoVO vo = new LoginAccountInfoVO();
		BeanUtils.copyProperties(userAccount, vo);
		vo.setAccountTypeName(DictHolder.getDictItemName("accountType", vo.getAccountType()));
		return vo;
	}

}
