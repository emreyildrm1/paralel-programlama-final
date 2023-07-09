



import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main extends Thread {
    public static final String PERSON_URL = "c:\\io\\turgutozaluniversitesi\\person.txt";
    public static final String SECRET_URL = "c:\\io\\turgutozaluniversitesi\\secret.txt";

    public String kullaniciBilAl() {
        Scanner klavye = new Scanner(System.in);
        String userName, pass, email;

        System.out.println("Kullanıcı Adı: ");
        userName = klavye.nextLine();

        System.out.println("Şifre: ");
        pass = klavye.nextLine();

        System.out.println("E-posta: ");
        email = klavye.nextLine();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Kullanıcı Adı: ").append(userName).append("\n");
        stringBuilder.append("Şifre: ").append(pass).append("\n");
        stringBuilder.append("E-posta: ").append(email);

        return stringBuilder.toString();
    }

    public void kullaniciBilgiKaydet() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PERSON_URL, false))) {
            String kullaniciBilgi = kullaniciBilAl();
            bufferedWriter.write(kullaniciBilgi);
            bufferedWriter.flush();
            System.out.println("Kullanıcı bilgilerini " + PERSON_URL + "'a kaydedildi.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String secretinformation() {
        Scanner klavye = new Scanner(System.in);
        String gizliBilgi;
        System.out.println("Gizli Bilgi: ");
        gizliBilgi = klavye.nextLine();
        return gizliBilgi;
    }

    public void gizliBilgiyiKaydet() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(SECRET_URL, false))) {
            String gizliBilgi = gizliBilgiAl();
            bufferedWriter.write(gizliBilgi);
            bufferedWriter.flush();
            System.out.println("Gizli bilgiler " + SECRET_URL + "'a kaydedildi.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Main thread1 = new Main();
        thread1.kullaniciBilgileriniKaydet();

        Main thread2 = new Main();
        thread2.gizliBilgiyiKaydet();


        thread1.start();
        thread1.join();

        thread2.start();
        thread2.join();

        System.out.println("Program tamamlandı.");
    }
}