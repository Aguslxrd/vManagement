package org.aguslxrd.Modules;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AuthLogs {
    static String line;
    String[] cmd = {"grep -E \"sshd|CRON|pam_unix\" \"/var/log/auth.log\""};
    Process pb = Runtime.getRuntime().exec(cmd);

    public AuthLogs() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(pb.getInputStream()));
        while ((line = input.readLine()) != null) {
            System.out.println(line);
        }
        input.close();
    }
}

