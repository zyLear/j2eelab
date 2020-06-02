package com.zylear.j2eelab.base;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Merge {

    public static void main(String[] args) throws IOException {

        String sql = "select id, (select id  from t_social_wechat_user where qwxid = {new_qwxid}) from t_social_wechat_user where qwxid ={old_qwxid} union";
        String sql1 = "update t_wechat_user set is_deleted = 1 where id = {old_qwxid} ;\n"  ;// +
//                "\n" +
//                "update t_wechat_group_invite_record set invitee_qwxid = {new_qwxid} where invitee_qwxid = {old_qwxid};\n" +
//                "update t_wechat_group_invite_record set inviter_qwxid = {new_qwxid} where inviter_qwxid = {old_qwxid};\n" +
//                " \n" +
//                "update t_wechat_user_friend_phone set friend_qwxid = {new_qwxid} where friend_qwxid = {old_qwxid};\n" +
//                "update t_wechat_user_friend_tag set friend_qwxid = {new_qwxid} where friend_qwxid = {old_qwxid};";

        String sql11 = "select concat('update t_wechat_group_relation set is_deleted = 1 where id =',id) from t_wechat_group_relation \n" +
                "where is_deleted = 0 and  qwxid  = {old_qwxid}  and group_id in (\n" +
                "select group_id from t_wechat_group_relation where qwxid = {new_qwxid}) ;\n" +
                "select concat('update t_wechat_group_relation set qwxid = {new_qwxid} where id =',id) from t_wechat_group_relation \n" +
                "where  is_deleted = 0 and  qwxid  = {old_qwxid}  and group_id not in (\n" +
                "select group_id from t_wechat_group_relation where qwxid = {new_qwxid}) ;\n" +
                "select concat('update t_wechat_user_friend set is_deleted = 1 where id =',id) from t_wechat_user_friend \n" +
                "where is_deleted = 0 and  friend_qwxid  = {old_qwxid}  and qwxid in (\n" +
                "select qwxid from t_wechat_user_friend where friend_qwxid = {new_qwxid} and is_deleted = 0) ;\n" +
                "select concat('update t_wechat_user_friend set friend_qwxid = {new_qwxid} where id =',id) from t_wechat_user_friend \n" +
                "where is_deleted = 0 and  friend_qwxid  = {old_qwxid}  and qwxid not in (\n" +
                "select qwxid from t_wechat_user_friend where friend_qwxid = {new_qwxid} and is_deleted = 0) ;";


        String sql2 = "select concat('update t_social_wechat_group_member set is_deleted = 1 where id =',id) from t_social_wechat_group_member\n" +
                "where is_deleted = 0 and  social_wxid  = {old_social_wxid}  and social_group_id in (\n" +
                "select social_group_id from t_social_wechat_group_member where social_wxid = {new_social_wxid} ) ;\n" +
                "select concat('update t_social_wechat_group_member set social_wxid = {new_social_wxid} where id =',id) from t_social_wechat_group_member\n" +
                "where  is_deleted = 0 and  social_wxid  = {old_social_wxid}  and social_group_id not in (\n" +
                "select social_group_id from t_social_wechat_group_member where social_wxid = {new_social_wxid} ) ;\n" +
                "select concat('update t_social_wechat_friend set is_deleted = 1 where id =',id) from t_social_wechat_friend\n" +
                "where is_deleted = 0 and  friend_social_wxid  = {old_social_wxid}  and social_wxid in (\n" +
                "select social_wxid from t_social_wechat_friend where friend_social_wxid = {new_social_wxid} and is_deleted = 0) ;\n" +
                "select concat('update t_social_wechat_friend set friend_social_wxid = {new_social_wxid} where id =',id) from t_social_wechat_friend\n" +
                "where is_deleted = 0 and  friend_social_wxid  = {old_social_wxid}  and social_wxid not in (\n" +
                "select social_wxid from t_social_wechat_friend where friend_social_wxid = {new_social_wxid} and is_deleted = 0) ;\n" +
                " \n" +
                "select concat('update t_social_wechat_user_tag set is_deleted = 1 where id =',id) from t_social_wechat_user_tag\n" +
                "where is_deleted = 0 and  social_wxid  = {old_social_wxid}  and tag_id in (\n" +
                "select tag_id from t_social_wechat_user_tag where social_wxid = {new_social_wxid} and is_deleted = 0) ;\n" +
                "select concat('update t_social_wechat_user_tag set social_wxid = {new_social_wxid} where id =',id) from t_social_wechat_user_tag\n" +
                "where is_deleted = 0 and  social_wxid  = {old_social_wxid}  and tag_id not in (\n" +
                "select tag_id from t_social_wechat_user_tag where social_wxid = {new_social_wxid} and is_deleted = 0) ;\n" +
                " \n" +
                "select concat('update t_social_wechat_user_black_white_list set is_deleted = 1 where id =',id) from t_social_wechat_user_black_white_list\n" +
                "where is_deleted = 0 and  social_wxid  = {old_social_wxid}  and list_type in (\n" +
                "select list_type from t_social_wechat_user_black_white_list where social_wxid = {new_social_wxid} and is_deleted = 0 ) ;\n" +
                " \n" +
                "select concat('update t_social_wechat_user_black_white_list set social_wxid = {new_social_wxid} where id =',id) from t_social_wechat_user_black_white_list\n" +
                "where  is_deleted = 0 and  social_wxid  = {old_social_wxid} and list_type not in (\n" +
                "select list_type from t_social_wechat_user_black_white_list where social_wxid = {new_social_wxid} and is_deleted = 0) ;";


        String sql3 = "SELECT\n" +
                "    concat(\n" +
                "        'update qq_ts.t_wechat_can_not_send_message_relation set is_deleted = 1 where id =',\n" +
                "        id\n" +
                "    )\n" +
                "FROM\n" +
                "    qq_ts.t_wechat_can_not_send_message_relation\n" +
                "WHERE\n" +
                "    is_deleted = 0\n" +
                "AND customer_qwxid = {old_qwxid}\n" +
                "AND assistant_qwxid IN (\n" +
                "    SELECT\n" +
                "        assistant_qwxid\n" +
                "    FROM\n" +
                "        qq_ts.t_wechat_can_not_send_message_relation\n" +
                "    WHERE\n" +
                "        customer_qwxid = {new_qwxid}\n" +
                ");\n" +
                "SELECT\n" +
                "    concat(\n" +
                "        'update qq_ts.t_wechat_can_not_send_message_relation set customer_qwxid = {new_qwxid} where id =',\n" +
                "        id\n" +
                "    )\n" +
                "FROM\n" +
                "    qq_ts.t_wechat_can_not_send_message_relation\n" +
                "WHERE\n" +
                "    is_deleted = 0\n" +
                "AND customer_qwxid = {old_qwxid}\n" +
                "AND assistant_qwxid NOT IN (\n" +
                "    SELECT\n" +
                "        assistant_qwxid\n" +
                "    FROM\n" +
                "        qq_ts.t_wechat_can_not_send_message_relation\n" +
                "    WHERE\n" +
                "        customer_qwxid = {new_qwxid}\n" +
                ");\n" +
                "  \n" +
                "SELECT\n" +
                "    concat(\n" +
                "        'update qq_ts.t_wechat_customer_assistant_abnormal_relation set is_deleted = 1 where id =',\n" +
                "        id\n" +
                "    )\n" +
                "FROM\n" +
                "    qq_ts.t_wechat_customer_assistant_abnormal_relation\n" +
                "WHERE\n" +
                "    is_deleted = 0\n" +
                "AND customer_qwxid = {old_qwxid}\n" +
                "AND assistant_qwxid IN (\n" +
                "    SELECT\n" +
                "        assistant_qwxid\n" +
                "    FROM\n" +
                "        qq_ts.t_wechat_customer_assistant_abnormal_relation\n" +
                "    WHERE\n" +
                "        customer_qwxid = {new_qwxid}\n" +
                ");\n" +
                "SELECT\n" +
                "    concat(\n" +
                "        'update qq_ts.t_wechat_customer_assistant_abnormal_relation set customer_qwxid = {new_qwxid} where id =',\n" +
                "        id\n" +
                "    )\n" +
                "FROM\n" +
                "    qq_ts.t_wechat_customer_assistant_abnormal_relation\n" +
                "WHERE\n" +
                "    is_deleted = 0\n" +
                "AND customer_qwxid = {old_qwxid}\n" +
                "AND assistant_qwxid NOT IN (\n" +
                "    SELECT\n" +
                "        assistant_qwxid\n" +
                "    FROM\n" +
                "        qq_ts.t_wechat_customer_assistant_abnormal_relation\n" +
                "    WHERE\n" +
                "        customer_qwxid = {new_qwxid}\n" +
                ");\n" +
                "  \n" +
                "SELECT\n" +
                "    concat(\n" +
                "        'update qq_ts.t_wechat_group_user set is_deleted = 1 where id =',\n" +
                "        id\n" +
                "    )\n" +
                "FROM\n" +
                "    qq_ts.t_wechat_group_user\n" +
                "WHERE\n" +
                "    is_deleted = 0\n" +
                "AND wx_user_id = {old_qwxid}\n" +
                "AND group_id IN (\n" +
                "    SELECT\n" +
                "        group_id\n" +
                "    FROM\n" +
                "        qq_ts.t_wechat_group_user\n" +
                "    WHERE\n" +
                "        wx_user_id = {new_qwxid}\n" +
                ");\n" +
                "SELECT\n" +
                "    concat(\n" +
                "        'update qq_ts.t_wechat_group_user set wx_user_id = {new_qwxid} where id =',\n" +
                "        id\n" +
                "    )\n" +
                "FROM\n" +
                "    qq_ts.t_wechat_group_user\n" +
                "WHERE\n" +
                "    is_deleted = 0\n" +
                "AND wx_user_id = {old_qwxid}\n" +
                "AND group_id NOT IN (\n" +
                "    SELECT\n" +
                "        group_id\n" +
                "    FROM\n" +
                "        qq_ts.t_wechat_group_user\n" +
                "    WHERE\n" +
                "        wx_user_id = {new_qwxid}\n" +
                ");\n" +
                "  \n" +
                "  \n" +
                "SELECT\n" +
                "    concat(\n" +
                "        'update qq_ts.t_wechat_user_friend set is_deleted = 1 where id =',\n" +
                "        id\n" +
                "    )\n" +
                "FROM\n" +
                "    qq_ts.t_wechat_user_friend\n" +
                "WHERE\n" +
                "    is_deleted = 0\n" +
                "AND friend_qwxid = {old_qwxid}\n" +
                "AND qwxid IN (\n" +
                "    SELECT\n" +
                "        qwxid\n" +
                "    FROM\n" +
                "        qq_ts.t_wechat_user_friend\n" +
                "    WHERE\n" +
                "        friend_qwxid = {new_qwxid}\n" +
                ");\n" +
                "SELECT\n" +
                "    concat(\n" +
                "        'update qq_ts.t_wechat_user_friend set friend_qwxid = {new_qwxid} where id =',\n" +
                "        id\n" +
                "    )\n" +
                "FROM\n" +
                "    qq_ts.t_wechat_user_friend\n" +
                "WHERE\n" +
                "    is_deleted = 0\n" +
                "AND friend_qwxid = {old_qwxid}\n" +
                "AND qwxid NOT IN (\n" +
                "    SELECT\n" +
                "        qwxid\n" +
                "    FROM\n" +
                "        qq_ts.t_wechat_user_friend\n" +
                "    WHERE\n" +
                "        friend_qwxid = {new_qwxid}\n" +
                ");\n" +
                "  \n" +
                "SELECT\n" +
                "    concat(\n" +
                "        'update qq_ts.t_wechat_user_role_relation set is_deleted = 1 where id =',\n" +
                "        id\n" +
                "    )\n" +
                "FROM\n" +
                "    qq_ts.t_wechat_user_role_relation\n" +
                "WHERE\n" +
                "    is_deleted = 0\n" +
                "AND wx_user_id = {old_qwxid}\n" +
                "AND user_id IN (\n" +
                "    SELECT\n" +
                "        user_id\n" +
                "    FROM\n" +
                "        qq_ts.t_wechat_user_role_relation\n" +
                "    WHERE\n" +
                "        wx_user_id = {new_qwxid}\n" +
                ");\n" +
                "SELECT\n" +
                "    concat(\n" +
                "        'update qq_ts.t_wechat_user_role_relation set wx_user_id = {new_qwxid} where id =',\n" +
                "        id\n" +
                "    )\n" +
                "FROM\n" +
                "    qq_ts.t_wechat_user_role_relation\n" +
                "WHERE\n" +
                "    is_deleted = 0\n" +
                "AND wx_user_id = {old_qwxid}\n" +
                "AND user_id NOT IN (\n" +
                "    SELECT\n" +
                "        user_id\n" +
                "    FROM\n" +
                "        qq_ts.t_wechat_user_role_relation\n" +
                "    WHERE\n" +
                "        wx_user_id = {new_qwxid}\n" +
                ");";

        String sql4 = "select\n" +
                "    case\n" +
                "        when a.c = 0 then concat('update qq_ta.t_customer_receive_wechat_message_count set crm_qwxid = {new_qwxid} where crm_qwxid = {old_qwxid} and is_deleted = 0')\n" +
                "        when a.c > 0 then concat('update qq_ta.t_customer_receive_wechat_message_count set is_deleted = 1 where crm_qwxid = {old_qwxid}')\n" +
                "    end\n" +
                "from\n" +
                "(select count(*) as c from qq_ta.t_customer_receive_wechat_message_count where crm_qwxid = {new_qwxid}) a;";

        String sql5 = "update t_ta_wechat_message_group_send_status set qwxid_receiver = {new_qwxid} where qwxid_receiver = {old_qwxid};";




        String sql111 = "update t_wechat_group_invite_record set invitee_qwxid = {new_qwxid} where invitee_qwxid = {old_qwxid};\n" +
                "update t_wechat_group_invite_record set inviter_qwxid = {new_qwxid} where inviter_qwxid = {old_qwxid};\n" +
                "\n" +
                "update t_wechat_user_friend_phone set friend_qwxid = {new_qwxid} where friend_qwxid = {old_qwxid};\n" +
                "update t_wechat_user_friend_tag set friend_qwxid = {new_qwxid} where friend_qwxid = {old_qwxid};";

//        List<String> list = FileUtils.readLines(new File("C:\\Users\\xiezongyu\\Documents\\txt-record\\qwxid-map.txt"));
//        StringBuilder stringBuilder = new StringBuilder();
//        for (String string : list) {
//            String[] strings = string.split("\t");
//            stringBuilder.append(sql.replace("{old_qwxid}", strings[0]).replace("{new_qwxid}", strings[1])).append("\n");
//        }
//        System.out.println(stringBuilder.toString());

//        List<String> list = FileUtils.readLines(new File("C:\\Users\\xiezongyu\\Documents\\txt-record\\qwxid-map.txt"));
//        StringBuilder stringBuilder = new StringBuilder();
//        for (String string : list) {
//            String[] strings = string.split("\t");
//            stringBuilder.append(sql111.replace("{old_qwxid}", strings[0])
//                    .replace("{new_qwxid}", strings[1])).append("\n\n\n");
//        }
//        System.out.println(stringBuilder.toString());


//        List<String> list = FileUtils.readLines(new File("C:\\Users\\xiezongyu\\Documents\\txt-record\\social_wxid_map.txt"));
//        StringBuilder stringBuilder = new StringBuilder();
//        for (String string : list) {
//            String[] strings = string.split("\t");
//            stringBuilder.append(sql2.replace("{old_social_wxid}", strings[0])
//                    .replace("{new_social_wxid}", strings[1])).append("\n\n\n");
//        }
//        System.out.println(stringBuilder.toString());

//        List<String> list = FileUtils.readLines(new File("C:\\Users\\xiezongyu\\Documents\\txt-record\\qwxid-map.txt"));
//        StringBuilder stringBuilder = new StringBuilder();
//        for (String string : list) {
//            String[] strings = string.split("\t");
//            stringBuilder.append(sql4.replace("{old_qwxid}", strings[0])
//                    .replace("{new_qwxid}", strings[1])).append("\n\n\n");
//        }
//        System.out.println(stringBuilder.toString());

//        List<String> list = FileUtils.readLines(new File("C:\\Users\\xiezongyu\\Documents\\txt-record\\qwxid-map.txt"));
//        StringBuilder stringBuilder = new StringBuilder();
//        for (String string : list) {
//            String[] strings = string.split("\t");
//            stringBuilder.append(sql5.replace("{old_qwxid}", strings[0])
//                    .replace("{new_qwxid}", strings[1])).append("\n\n\n");
//        }
//        System.out.println(stringBuilder.toString());




        List<String> list = FileUtils.readLines(new File("C:\\Users\\xiezongyu\\Documents\\txt-record\\qwxid-map.txt"));
        StringBuilder stringBuilder = new StringBuilder("(");
        for (String string : list) {
            String[] strings = string.split("\t");
            stringBuilder.append(strings[0]).append(",");
        }
        System.out.println(stringBuilder.toString());

    }

}
