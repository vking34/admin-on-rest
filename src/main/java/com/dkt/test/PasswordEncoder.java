package com.dkt.test;

import org.bson.types.ObjectId;

import java.io.IOException;

public class PasswordEncoder {

    public static void main(String args[]) throws IOException {

//        ObjectMapper objectMappe = new ObjectMapper();
//        Acc account1 = objectMappe.readValue("{\n" +
//                " \"id\": {\n" +
//                "  \"timestamp\": 1481276779,\n" +
//                "  \"machine_identifier\": 1876950,\n" +
//                "  \"process_identifier\": 17700,\n" +
//                "  \"counter\": 4368153,\n" +
//                "  \"date\": 1481276779000,\n" +
//                "  \"time\": 1481276779000,\n" +
//                "  \"time_second\": 1481276779\n" +
//                " },\n" +
//                " \"name\": \"Dana Nguyễn\",\n" +
//                " \"created_on\": 1481276779000,\n" +
//                " \"modified_on\": 1533718356339,\n" +
//                " \"page_maps\": []\n" +
//                "}", Acc.class);

        ObjectId objectId1 = new ObjectId(1467385679, 1876950, (short) 23084,2728892);
        ObjectId objectId2 = new ObjectId(1527998922, 15177507, (short) 1,1245035);

        System.out.println(objectId1.toString());
        System.out.println(objectId2.toString());
//        Map<String, String> obj = new HashMap<String, String>("{\n" +
//                " \"id\": {\n" +
//                "  \"timestamp\": 1481276779,\n" +
//                "  \"machine_identifier\": 1876950,\n" +
//                "  \"process_identifier\": 17700,\n" +
//                "  \"counter\": 4368153,\n" +
//                "  \"date\": 1481276779000,\n" +
//                "  \"time\": 1481276779000,\n" +
//                "  \"time_second\": 1481276779\n" +
//                " },\n" +
//                " \"name\": \"Dana Nguyễn\",\n" +
//                " \"created_on\": 1481276779000,\n" +
//                " \"modified_on\": 1533718356339,\n" +
//                " \"page_maps\": []\n" +
//                "}"));
//
//        System.out.println(obj.get("id"));

//        Account account2 = objectMappe.readValue("{\n" +
//                " \"id\": {\n" +
//                "  \"timestamp\": 1527998922,\n" +
//                "  \"machine_identifier\": 15177507,\n" +
//                "  \"process_identifier\": 1,\n" +
//                "  \"counter\": 1245035,\n" +
//                "  \"date\": 1527998922000,\n" +
//                "  \"time\": 1527998922000,\n" +
//                "  \"time_second\": 1527998922\n" +
//                " },\n" +
//                " \"facebook_user_id\": \"1598171123843269\",\n" +
//                " \"name\": \"Mai Que\",\n" +
//                " \"email\": null,\n" +
//                " \"sccess_token\": null,\n" +
//                " \"created_on\": 1527998922000,\n" +
//                " \"modified_on\": 1533715347515,\n" +
//                " \"last_login_on\": null,\n" +
//                " \"page_maps\": null,\n" +
//                " \"bizweb_customer_maps\": [{\n" +
//                "  \"page_id\": {\n" +
//                "   \"timestamp\": 1527039795,\n" +
//                "   \"machine_identifier\": 10429244,\n" +
//                "   \"process_identifier\": 1,\n" +
//                "   \"counter\": 12828953,\n" +
//                "   \"date\": 1527039795000,\n" +
//                "   \"time\": 1527039795000,\n" +
//                "   \"time_second\": 1527039795\n" +
//                "  },\n" +
//                "  \"bizweb_customer_id\": 8565437,\n" +
//                "  \"name\": \"Mai Que\",\n" +
//                "  \"email\": null\n" +
//                " }],\n" +
//                " \"has_banned\": null,\n" +
//                " \"messenger_user_maps\": [{\n" +
//                "  \"mp_user_id\": \"1839067692836757\",\n" +
//                "  \"fb_page_id\": \"898522970278890\"\n" +
//                " },\n" +
//                " {\n" +
//                "  \"mp_user_id\": \"1617829474943138\",\n" +
//                "  \"fb_page_id\": \"864345120260929\"\n" +
//                " }],\n" +
//                " \"bizweb_orders\": [{\n" +
//                "  \"bizweb_order_id\": 4772728,\n" +
//                "  \"sent_customer_id\": 8565437,\n" +
//                "  \"received_customer_id\": 8565437,\n" +
//                "  \"facebook_page_id\": \"864345120260929\",\n" +
//                "  \"page_id\": \"5b04c7339f233c0001c3c119\",\n" +
//                "  \"bizweb_store_alias\": \"maihousestore\"\n" +
//                " }]\n" +
//                "}", Account.class);
//
//        System.out.println(account1.getId());
//        ObjectId objectId = new ObjectId(account1.getId().toString());
//        System.out.println(objectId);

//        System.out.println(account2.getId());
    }

}
