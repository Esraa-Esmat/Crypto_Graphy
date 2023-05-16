/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bounasTask;

/**
 *
 * @author Esraa Esmat
 */
import java.util.*;

public class OTP_StartFinal {

    public static void main(String[] args) {
        //  String[] MSGS = new String[11];

        final String[] MSGS
                = /*Msg1*/ {"2D26B875C137281068E2615E6A3E9BB11C671D9284E3341E384B12C79FFC9E24D164D37003F4D45564FC3F5410695F0CE0099A63637498C6B73F08BD2EB7421083DD7273C522D6ED58505D4558A4CB824C6F6E11960CD8F538886EA3",
                    /*Msg2*/ "0926BD75DA72334062F8795E602CD5F8132B029CD0FF295B79511E8E85A8CA2CC869D36904E59A1471E42B0D0D2C580DE01097672D7F93D2AF7A18A661AA440DD9DD4C78C522CCE11D561C525CE3D18C4F642A459C128CB47F8965B5",
                    /*Msg3*/ "0D26B875C137281068E2615E6A3E9BAC1A22519E9FF92E1E3A4A1B8A99E69E2FDF6F9E3E03E6D4164FF93B480820530EE253D24E2C6198C3A66D4BB82EAC4455CC9341368839D6E11D44185E47E8C6C35B782B45801DCFBE7F8C6EA2",
                    /*Msg4*/ "1521A3638C632E0F77B77B1F706DD8AA1726059694AA385B3A44039493A8C92C906A927018E590147AEA30490C241D1DA505862625798F95AC6A19F536BB4355C998567F8238D7AA1D63155459A4D4861A79260A8454CDF53B8873AF",
                    /*Msg5*/ "611AA530DB7F384068FF630B6F29D5FF06670696D0E02F4D2D05158886F19339D16E877B4CE1D44761E5394106694E0CAE0997682073DDD4AD7B4BB224AA01148D9F4979863D84EB5B1409544FF083DC1A422F139654CDF533826FAD",
                    /*Msg6*/ "613DA271D83726056FE42C12662BCFF80628518498E339563C531395D6E1D03DD56F9D3E1BE187147DE53842113D4807A1099726267892C0A4774BA12EFE5614C19605708C24D7F01D5D134558A4D78B5F2A2103951DCFB07F9968A7",
                    /*Msg7*/ "6108A67C8C56230F6EE32C3D6A3FD8AD1B3302D399F97A4E2C471A8E85E0D727D73D923E1FE5865D6DF87E4205695C1BB414916A2665DDDAAD3F0EB823BB4511C89905658035D1F65440041140EDD78B1A6B6E168706C3BB38CD66A9",
                    /*Msg8*/ "153BB330C47633047EE52C136C3ECFF81D21518798E3291E2D400E93D6E1CD69DD78927005EE93586DF82D0C433C5305A50E81263A798895A57008A032FE4E1B8D894D73C53BC5ED5314095045E3C6971A7D260C901C8CBC2CCD74AE",
                    /*Msg9*/ "003AB965C172611473F6785E6A6DD3B90422519795EC33503C41568993FF9E1BD173977101A8C00621AB3743432A5108B30ED2672D72DDDCAD3F0ABB2EAA4910DFDD467A8425D7A454141C5656EDCDC35E656E0B96038C873E8364A9",
                    /*Msg10*/ "083DEA7DC57029143BE4691B6E6DCEB600221D9284EF3E1E2A4C188493A8D73D9070966C09EC8D147BEE3B4010694906E01F972622368FD0A57E08A12EAC0117D889057F9176CDF71D5018575EEACA975F6637458111C0B42B8864E8",
                    /*Target*/ "1521AF30DF7222127EE32C13663EC8B91522519A83B07A69314018C783FBD727D73D923E1FF4865169E67E4E0A39550CB25D9C6335738F95B66C0EF535B64455C6985C368839D6E11D40155059A4CC8D596F6E4DC2429EE26ED439EF"};

        List<String> cipher = new ArrayList<>();

        for (int i = 0; i < 11; i++) {
            cipher.add(hexToString(splitHex(MSGS[i])));
        }

        for (int j = 1; j < 11; j++) {
            List<String> result = new ArrayList<>();
            for (int i = 1; i < 11; i++) {
                if (i == j) {
                    continue;
                }
                String raw = strxor(cipher.get(j), cipher.get(i));
                char[] rawChars = raw.toCharArray();
                for (int k = 0; k < rawChars.length; k++) {
                    char letter = rawChars[k];
                    if (((letter >= 0x41 && letter <= 0x5a) || (letter >= 0x61 && letter <= 0x7a) || (letter >= 0x30 && letter <= 0x39) || (letter == 0x3a) || (letter == 0x28) || (letter == 0x29))) {
                        continue;
                    } else {
                        rawChars[k] = '_';
                    }
                }
                result.add(String.valueOf(rawChars));
                System.out.println(String.valueOf(rawChars));
            }

            int length = cipher.get(j).length() / 2;

            char[] plain = new char[length];
            Arrays.fill(plain, '=');
            for (int i = 0; i < length; i++) {
                int count_ = 0;
                int countL = 0;
                char mark = '\0';
                boolean only = true;
                for (String string : result) {
                    char letter = string.charAt(i);
                    if (letter == '_') {
                        count_++;
                    } else {
                        countL++;
                        if (mark != '\0' && mark == Character.toLowerCase(letter)) {
                            continue;
                        } else if (mark != '\0' && mark != Character.toLowerCase(letter)) {
                            only = false;
                        }
                        mark = Character.toLowerCase(letter);
                    }
                }

                if (countL == 1) {
                    plain[i] = mark;
                } else if (countL > 1) {
                    if (only) {
                        plain[i] = mark;
                    }
                } else {
                    plain[i] = '#';
                }
            }
            System.out.println();
            System.out.println(String.valueOf(plain));

            String plaintext = "The nice thing about Keeyloq is now we cryptographers can drive a lot of fancy cars - Dan Bone";
            System.out.println(strxor(strxor(plaintext, cipher.get(3)), cipher.get(6)));
        }
    }

    public static String listToHex(List<Integer> lo) {
        StringBuilder sb = new StringBuilder();
        for (int i : lo) {
            sb.append(String.format("%02x", i));
        }
        return sb.toString();
    }

    public static List<Integer> splitHex(String h) {
        List<Integer> split = new ArrayList<>();
        for (int i = 0; i < h.length(); i += 2) {
            split.add(Integer.parseInt(h.substring(i, i + 2), 16));
        }
        return split;
    }

    public static String hexToString(List<Integer> hex) {
        StringBuilder sb = new StringBuilder();
        for (int i : hex) {
            sb.append((char) i);
        }
        return sb.toString();
    }

    public static String strxor(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int len = Math.min(a.length(), b.length());
        for (int i = 0; i < len; i++) {
            sb.append((char) (a.charAt(i) ^ b.charAt(i)));
        }
        return sb.toString();
    }

    public static String encrypt(String key, String msg) {
        String c = strxor(key, msg);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < c.length(); i++) {
            sb.append(String.format("%02x", (int) c.charAt(i)));
        }
        return sb.toString();
    }

    public static String decrypt(String key, String cipher) {
        String msg = strxor(key, hexToString(splitHex(cipher)));
        return msg;
    }
}
