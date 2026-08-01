package io.projectZ.orchestrator.application.service.internalProcess.BotIDGenerator;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/24/2026 - 11:28 AM
*/

import java.security.SecureRandom;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class BotIdentifierGenerator {


    private static final String[] ADJECTIVES = {
            "bright", "calm", "swift", "silent", "keen",
            "bold", "wise", "sharp", "brave", "lunar",
            "solar", "rapid", "grand", "cool", "warm"
    };

    private static final String[] NOUNS = {
            "mind", "ghost", "sage", "spark", "echo",
            "drift", "nova", "pulse", "orbit", "wave",
            "flame", "storm", "shard", "beam", "nebula"
    };
    protected static final String PREFIX = "BOT";

    private static final String ALPHANUM = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final int RAND_LEN = 4;
    private static final int MAX_ATTEMPTS = 100;

    private static final SecureRandom random = new SecureRandom();

    public static String generateRaw() {
//        String adj = pick(ADJECTIVES);
        String noun = pick(NOUNS);
        String rand = randomString(RAND_LEN);
        return String.format("%s-%s-%s", PREFIX, noun, rand);
    }

    private static String pick(String[] pool) {
        return pool[random.nextInt(pool.length)];
    }

    private static String randomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(ALPHANUM.charAt(random.nextInt(ALPHANUM.length())));
        }
        return sb.toString();
    }
}

