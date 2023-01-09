package com.ljs.shupao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljs.shupao.model.domain.Team;
import com.ljs.shupao.model.domain.User;
import com.ljs.shupao.model.dto.TeamQuery;
import com.ljs.shupao.model.request.TeamJoinRequest;
import com.ljs.shupao.model.request.TeamQuitRequest;
import com.ljs.shupao.model.request.TeamUpdateRequest;
import com.ljs.shupao.model.vo.TeamUserVO;

import java.util.List;

/**
* @author ljs
* @description 针对表【team(队伍)】的数据库操作Service
* @createDate 2023-01-04 17:57:26
*/
public interface TeamService extends IService<Team> {
    long addTeam(Team team, User loginUser);

    List<TeamUserVO> listTeams(TeamQuery teamQuery, boolean isAdmin);

    boolean updateTeam(TeamUpdateRequest teamUpdateRequest, User loginUser);

    boolean joinTeam(TeamJoinRequest teamJoinRequest,User loginUser);

    boolean quitTeam(TeamQuitRequest teamQuitRequest, User loginUser);

    boolean deleteTeam(long id,User loginUser);
}
