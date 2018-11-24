package com.guiji.process.agent;


import com.guiji.process.agent.model.CfgProcessOperVO;
import com.guiji.process.agent.model.CfgProcessVO;
import com.guiji.process.agent.model.CommandResult;
import com.guiji.process.agent.service.ProcessCfgService;
import com.guiji.process.agent.util.CommandUtils;
import com.guiji.process.agent.util.ProcessUtil;
import com.guiji.process.core.IProcessCmdHandler;
import com.guiji.process.core.message.CmdMessageVO;
import com.guiji.process.core.vo.CmdTypeEnum;
import com.guiji.process.core.vo.ProcessInstanceVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class ProcessAgentCmdHandler implements IProcessCmdHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void excute(CmdMessageVO cmdMessageVO) throws  Exception {
        if(cmdMessageVO == null)
        {
            return;
        }

        ProcessInstanceVO processInstanceVO = cmdMessageVO.getProcessInstanceVO();
        CfgProcessOperVO cfgProcessOperVO = getNodeOper(cmdMessageVO.getCmdType(), cmdMessageVO.getProcessInstanceVO().getPort());

        switch (cmdMessageVO.getCmdType()) {
            case REGISTER:
                break;

            case RESTART:
                doCmd(cmdMessageVO, cfgProcessOperVO);
                break;

            case UNKNOWN:
                break;

            case START:
                doCmd(cmdMessageVO, cfgProcessOperVO);
                break;

            case STOP:
                doCmd(cmdMessageVO, cfgProcessOperVO);
                Thread.sleep(1000);//等待1s查看是否关闭成功
                //TODO 检查是否停掉，如果进程还在则kill -9
                if (ProcessUtil.checkRun(processInstanceVO.getPort())) {
                    ProcessUtil.killProcess(cmdMessageVO.getProcessInstanceVO().getPort());
                }
                break;

            case HEALTH:

               // ProcessUtil.sendHealth(processInstanceVO.getPort(),processInstanceVO.getType(), cfgProcessOperVO,processInstanceVO.getName());
                doHealth(cmdMessageVO, cfgProcessOperVO);
                break;

            case PULBLISH_SELLBOT_BOTSTENCE:
                doCmd(cmdMessageVO, cfgProcessOperVO);
                break;

            case PULBLISH_FREESWITCH_BOTSTENCE:
                doCmd(cmdMessageVO, cfgProcessOperVO);
                break;
            default:
                break;
        }
    }


    private CfgProcessOperVO getNodeOper(CmdTypeEnum cmdTypeEnum, int port)
    {
        CfgProcessVO cfgProcessVO = ProcessCfgService.cfgMap.get(port);
        if(cfgProcessVO == null)
        {
            return null;
        }

        for (CfgProcessOperVO cfgProcessOperVO : cfgProcessVO.getCfgNodeOpers()  ) {

            if(cfgProcessOperVO.getCmdTypeEnum() == cmdTypeEnum)
            {
                return cfgProcessOperVO;
            }

        }
        return null;
    }



    private CommandResult doCmd(CmdMessageVO cmdMessageVO, CfgProcessOperVO cfgProcessOperVO) {

        CommandResult cmdResult = null;
        if (ProcessUtil.neetExecute(cmdMessageVO.getProcessInstanceVO().getPort(), cfgProcessOperVO.getCmdTypeEnum())) {

            String cmd = cfgProcessOperVO.getCmd();
            if(cmdMessageVO.getParameters() != null && !cmdMessageVO.getParameters().isEmpty())
            {
                cmd = MessageFormat.format(cfgProcessOperVO.getCmd(), cmdMessageVO.getParameters().toArray());
            }

            // 发起命令
            cmdResult = CommandUtils.exec(cmd);
            // 执行完命令保存结果到内存记录
            ProcessUtil.afterCMD(cmdMessageVO.getProcessInstanceVO().getPort(), cfgProcessOperVO.getCmdTypeEnum());
        }

        return cmdResult;
    }



    private void doHealth(CmdMessageVO cmdMessageVO, CfgProcessOperVO cfgProcessOperVO)
    {
        CommandResult cmdResult = doCmd(cmdMessageVO,cfgProcessOperVO);

        // 对结果分析


    }

}
