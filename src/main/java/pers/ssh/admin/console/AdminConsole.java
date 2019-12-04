package pers.ssh.admin.console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pers.ssh.admin.console.beans.CommandResponse;
import pers.ssh.admin.console.commands.CommandProcessor;
import pers.ssh.admin.console.utils.Logger;
import pers.ssh.admin.console.utils.StringUtils;

/**
 * Author:   Dennis Su
 * Date:     2019/12/1 9:54 上午
 * Description: The entry point for this admin console.
 */
public class AdminConsole {

    public static void main(final String[] args) {
//        String str = "this is \'Tom\' and \'Eric\'， this is \'Bruce lee\', he is a chinese, name is \'李小龙\'";
        String str = "user1 'Phone model 8' 'Black color, brand new' 1000 'Electronics' qq";
//        Pattern p = Pattern.compile("\'(.*?)\'");
////        Pattern p= Pattern.compile("(?<=\").*?(?=\")");
//        Matcher m=p.matcher(str);
//        int i=0;
//
//        List<String> ls = new ArrayList<>();
//        while(m.find())
//        {
////            str=str.replace(m.group(),""+(i++));
//
//            String foundStr = m.group().trim();
//            String[] parts = str.split(foundStr);
//            String[] frontParts = parts[0].split(" ");
//            ls.addAll(Arrays.asList(frontParts));
//            if(parts.length > 1){
//                str = parts[1];
//            }else{
//                // the end
//                str = "";
//            }
//
//            ls.add(foundStr.replaceAll("\'", ""));
//
//
////            System.out.println(part);
////            System.out.println(str);
////            System.out.println(m.group().replaceAll("\'", ""));
//        }
//
//        if(StringUtils.isNotBlank(str)){
//            String[] lastParts = str.split(" ");
//            ls.addAll(Arrays.asList(lastParts));
//        }
//
//        System.out.println(ls.size());
//        for (String l : ls) {
//            System.out.println(l);
//        }

//        System.out.println(str);

        Logger.debug("Starting...");

        final Scanner reader = new Scanner(System.in);
        final boolean done = false;
        String in = null;
        while (!done) {
            System.out.print("# ");
            try {
                in = reader.nextLine();
                final CommandResponse response = CommandProcessor.process(in);
                handleResponse(response);
            } catch (final Exception e) {
                Logger.error("Input error: " + in, e);
                printHelp();
            }
        }
    }

    private static void handleResponse(final CommandResponse commandResponse) {
        if (commandResponse == null) {
            return;
        }

        if (commandResponse.isSuccess()) {
            System.out.println(commandResponse.getResponse());
        } else {
            System.out.println("Error - " + commandResponse.getResponse());
        }
    }

    private static void printHelp() {
        // TODO
    }
}
