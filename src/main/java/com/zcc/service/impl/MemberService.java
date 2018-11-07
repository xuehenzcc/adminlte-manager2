/**
 * 版权：zcc
 * 作者：c0z00k8
 * @data 2018年8月27日
 */
package com.zcc.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zcc.entity.Member;
import com.zcc.mapper.MemberMapper;
import com.zcc.service.IMemberService;

/**
 * @author c0z00k8
 *
 */
@Service
public class MemberService extends ServiceImpl<MemberMapper, Member> implements IMemberService{

	
}
