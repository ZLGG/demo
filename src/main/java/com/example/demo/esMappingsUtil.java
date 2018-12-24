package com.example.demo;



import java.io.FileOutputStream;

import java.util.Arrays;

public class esMappingsUtil {
    public static void createMappings(String ddl)throws Exception{
        String[] types1 = new String[]{"tinyint","smallint","mediumint","boolean"};//integer
        String[] types2 = new String[]{"int","bigint","float","double"," real","bit","serial"};//long
        String[] types3 = new String[]{"date","datetime","timestamp","time","year","char","varchar","tinytext"};//text
        String[] strings = ddl.split(",");
        StringBuffer mappings = null;
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\123\\Desktop\\mappings.txt");
        for (int i = 0; i < strings.length; i++) {
            String[] split = strings[i].split("`");
            String ignore_above = "256";

            if (i == 0) {
                    mappings = init(split[1]);
                    mappings.append("\"id\": {\n" +
                            "        \"type\": \"long\"\n" +
                            "      }");
                }

            if (i != 0){

                if (!strings[i].contains("`")){
                    continue;
                }
                if (strings[i].contains("PRIMARY KEY")){
                    mappings.append("\n" +
                            "  }\n" +
                            "}");
                    System.out.println(mappings);
                    byte[] bytes = mappings.toString().getBytes();
                    try {
                        fileOutputStream.write(bytes);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("写入失败");
                    }
                    finally {
                        fileOutputStream.close();
                    }

                    return;
                }
                    mappings.append(",\n" +
                            "      \""+split[1]+"\": {\n" +
                            "        \"type\": ");

                    String s = split[2].split("\\(")[0].substring(1);
                    if (s.equals("varchar")){
                        String s1 = split[2].split("\\(")[1].split("\\)")[0];
                        if (Integer.valueOf(s1) > 256)
                        ignore_above = s1;
                    }

                    if (Arrays.asList(types1).contains(s)) {
                        mappings.append("\"integer\"\n" +
                                "        }");
                    }
                    else if (Arrays.asList(types2).contains(s)) {
                        mappings.append("\"long\"\n" +
                                "        }");
                    } else {
                        mappings.append("\"text\",\n" +
                                "            \"fields\": {\n" +
                                "              \"keyword\": {\n" +
                                "                \"type\": \"keyword\",\n" +
                                "                \"ignore_above\": "+ignore_above+"\n" +
                                "              }\n" +
                                "            }\n" +
                                "          }");
                    }
            }

        }
    }
    static StringBuffer init(String dbName)
    {
      StringBuffer mappings = new StringBuffer("\""+dbName+"\": {\n" +
              "    \"dynamic\": \"true\",\n" +
              "    \"_all\": {\n" +
              "      \"enabled\": false\n" +
              "      },\n" +
              "    \"properties\": {\n" +
              "      ");
      return mappings;
    }
}
