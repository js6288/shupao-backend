package com.ljs.shupao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljs.shupao.model.domain.UserTeam;
import com.ljs.shupao.mapper.UserTeamMapper;
import com.ljs.shupao.service.UserTeamService;
import org.springframework.stereotype.Service;

/**
* @author ljs
* @description 针对表【user_team(用户队伍关系)】的数据库操作Service实现
* @createDate 2023-01-04 18:00:06
*/
@Service
public class UserTeamServiceImpl extends ServiceImpl<UserTeamMapper, UserTeam>
    implements UserTeamService{

}




