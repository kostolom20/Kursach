import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.checkerframework.checker.units.qual.K;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class vktelegrambot extends TelegramLongPollingBot {
    private String WebHookPath;
    //****
    // P.S я своего бота сделал
    //****
    private final String botUserName="SendmsgToVk_bot";
    private final String botToken="1645431495:AAFifwgWOgt7Eje2_D2DyuSfUoUifYtMWwo";

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }


    @Override
    public void onUpdateReceived(Update update) {

        // Вывод Hello Григорий Текст 11
       /* SendMessage sendMessage=new SendMessage();
        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        sendMessage.setText("hello "+update.getMessage().getFrom().getFirstName()+ " " + "\ntext" + update.getMessage().getText());
        */

        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            String chatId = update.getMessage().getChatId().toString();
            switch(message)
            {
                case "/help":
                {
                    try {
                        execute(new SendMessage(chatId, "Чем я могу помочь?"));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText(message);
            setButtons(sendMessage);

            sendMsg(sendMessage);




        }

    }
    public void sendMsg(SendMessage sendMessage)
    {
        // повторение введённого с клавиатуры
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    //====================================
    // поытка вывести друзей которые онлайн
    public void getJsonResponse() throws IOException {
        String url = "https://api.vk.com/method/friends.getOnline?v=5.52&access_token=<ad90261093253cc56fe246e990433744d1d134f8ab8f95d974f0f0de542aed6b2026f0be4e35f62023f63>";

        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        System.out.println(response.toString());
    }
    //==============================
    public void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();

        KeyboardRow keyboardRowFirst = new KeyboardRow();

        keyboardRowFirst.add(new KeyboardButton("Hi\uD83D\uDC4B"));
        keyboardRowFirst.add(new KeyboardButton("Hi2"));

        KeyboardRow keyboardRowSecond = new KeyboardRow();
        keyboardRowSecond.add(new KeyboardButton("/help"));

        keyboardRowList.add(keyboardRowFirst);
        keyboardRowList.add(keyboardRowSecond);

        replyKeyboardMarkup.setKeyboard(keyboardRowList);


        //=======================================
        // попытка сделать кнопку под сообщением с перенаправлением по ссылке
        List<List<InlineKeyboardButton>> keyboardInlineList = new ArrayList<>();
        List<InlineKeyboardButton> inline = new ArrayList<>();


        InlineKeyboardButton in1 = new InlineKeyboardButton();
        in1.setText("Choose 1");
        in1.setUrl("https://tlgrm.ru/docs/bots/api#replykeyboardmarkup");
        //in1.getCallbackData();
        inline.add(in1);
        keyboardInlineList.add(inline);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboardInlineList);

        //=========================================

    }
}




