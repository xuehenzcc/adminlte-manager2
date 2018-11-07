/**
 * 版权：zcc
 * 作者：c0z00k8
 * @data 2018年8月28日
 */
package com.zcc.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zcc.entity.Message;
import com.zcc.mapper.MessageMapper;
import com.zcc.service.IMessageService;

/**
 * @author c0z00k8
 *
 */
@Service
public class MessageService extends ServiceImpl<MessageMapper, Message> implements IMessageService{

	
}
