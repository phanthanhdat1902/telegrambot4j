package com.mobicast.telegram4j.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobicast.telegram4j.domain.GitlabRequestBody;
import com.mobicast.telegram4j.service.TelegramService;
import com.mobicast.telegram4j.utils.Const;
import com.mobicast.telegram4j.utils.RequestUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Log4j2
public class TelegramImpl implements TelegramService {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void notify(String requestBody) {
        StringBuilder message = new StringBuilder();
        try {
            GitlabRequestBody gitlabRequestBody = mapper.readValue(requestBody, GitlabRequestBody.class);
            // delete branch
            if (gitlabRequestBody.after != null && gitlabRequestBody.before != null) {
                if (gitlabRequestBody.after.equals(Const.GITLAB.HASH_CODE_DELETE_BRANCH)) {
                    message.append(" Huhu, Suger Daddy @");
                    message.append(Const.TELEGRAM_USER_ID.TELEGRAM_USER.get(gitlabRequestBody.getUser_username()));
                    message.append(" - ");
                    message.append(gitlabRequestBody.getUser_name());
                    message.append(Const.ACTION.DELETE_BRANCH);
                    message.append(gitlabRequestBody.getProject().getName() + "/");
                    message.append(gitlabRequestBody.getRef().replace("refs", "").replace("/head/", ""));
                    sendMessage(message.toString());
                }
            }

            //request merge
            if (gitlabRequestBody.getAssignees() != null && gitlabRequestBody.getReviewers() != null) {
                if (gitlabRequestBody.getObject_attributes().getMerge_status().equals(Const.GITLAB.REQUEST_MERGE_STATUS)) {
                    message.append(" A normal day in Mobicast: Bé ");
                    message.append(gitlabRequestBody.getAssignees().get(0).getName());
                    message.append(" 18 tuổi yêu cầu ");
                    message.append(gitlabRequestBody.getReviewers().get(0).getName());
                    message.append(" xem và merge code từ nhánh ");
                    message.append(gitlabRequestBody.getObject_attributes().getSource_branch());
                    message.append(" vào nhánh ");
                    message.append(gitlabRequestBody.getObject_attributes().getTarget_branch());
                    message.append(" @");
                    message.append(Const.TELEGRAM_USER_ID.TELEGRAM_USER.get(gitlabRequestBody.getReviewers().get(0).getUsername()));
                    sendMessage(message.toString());
                } else if (gitlabRequestBody.getObject_attributes().getMerge_status().equals(Const.GITLAB.MERGE_DONE)) {
                    message.append(" Thầy ông nội ");
                    message.append(gitlabRequestBody.getReviewers().get(0).getName());
                    message.append(" đã merge xong code từ nhánh ");
                    message.append(gitlabRequestBody.getObject_attributes().getSource_branch());
                    message.append(" vào nhánh ");
                    message.append(gitlabRequestBody.getObject_attributes().getTarget_branch());
                    message.append(" @");
                    message.append(Const.TELEGRAM_USER_ID.TELEGRAM_USER.get(gitlabRequestBody.getReviewers().get(0).getUsername()));
                    message.append(". Quá trình ci/cd đang được thực thi, hãy chờ trong giây lát và thông báo với team QA rằng server đang khởi động.");
                    message.append(" Trong quá trình chờ, nếu thấy nhớ người yêu cũ, hãy nghe ngay: https://www.youtube.com/watch?v=CQ3Mk4zmiFk.");
                    sendMessage(message.toString());
                }
            }

            //merge code done

        } catch (Exception e) {
            log.error(e.toString());
        }
    }

    public static void sendMessage(String message) {
        List<String> segment = new ArrayList<>();
        segment.add(Const.TELEGRAM.BOT_SEGMENT);
        segment.add(Const.TELEGRAM.ACTION);
        HashMap<String, String> params = new HashMap<>();
        params.put("chat_id", Const.TELEGRAM.GROUP_ID);
        params.put("text", message);
        RequestUtil.get(Const.TELEGRAM.URL, segment, params);
    }
}
