package com.example.norbertbarczyk.blackjackandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class BlackJack extends AppCompatActivity {
    //initialize ImageViews
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private ImageView imageView6;
    private ImageView imageView7;
    private ImageView imageView8;
    private ImageView imageView9;
    private ImageView imageView10;
    private ImageView imageView11;
    private ImageView imageView12;


    boolean endRound = false;
    private TextView GameText;
    private EditText PlayerInput;
    private Button EnterBtn;
    private TextView DealerPoints;
    private TextView YourPoints;
    private TextView WinLosePush;
    private TextView PlayerMoney;
    int playerMoney = 300;
    int playerValue;
    int playerBet;
    int cardNumber = 0;
    Button hit,stand,play,leave;

    Deck playingDeck = new Deck();
    //Create a deck for the player
    Deck playerDeck = new Deck();

    Deck dealerDeck = new Deck();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack);
        //find imageViews
        imageView1 = (ImageView) findViewById(R.id.card1);
        imageView2 = (ImageView) findViewById(R.id.card2);
        imageView3 = (ImageView) findViewById(R.id.card3);
        imageView4 = (ImageView) findViewById(R.id.card4);
        imageView5 = (ImageView) findViewById(R.id.card5);
        imageView6 = (ImageView) findViewById(R.id.card6);
        imageView7 = (ImageView) findViewById(R.id.card7);
        imageView8 = (ImageView) findViewById(R.id.card8);
        imageView9 = (ImageView) findViewById(R.id.card9);
        imageView10 = (ImageView) findViewById(R.id.card10);
        imageView11 = (ImageView) findViewById(R.id.card11);
        imageView12 = (ImageView) findViewById(R.id.card12);

        GameText = (TextView) findViewById(R.id.gameText);
        PlayerInput = (EditText) findViewById(R.id.playerInputField);
        EnterBtn = (Button) findViewById(R.id.enter);
        GameText.setText("Welcome to BlackJack");
        GameText.setMovementMethod(new ScrollingMovementMethod());
        hit = (Button)findViewById(R.id.hitBtn);
        stand = (Button)findViewById(R.id.standBtn);
        play = (Button)findViewById(R.id.playBtn);
        leave = (Button)findViewById(R.id.leaveBtn);
        YourPoints = (TextView)findViewById(R.id.playerPoints);
        DealerPoints = (TextView)findViewById(R.id.dealerPoints);
        WinLosePush = (TextView)findViewById(R.id.winlosepush);
        PlayerMoney = (TextView)findViewById(R.id.PlayerMoneyTV);

        hit.setEnabled(false);
        stand.setEnabled(false);
        play.setEnabled(false);



    }

    @Override
    protected void onStart() {
        super.onStart();
        EnterBtn.setEnabled(true);
        setBet();



    }

//setting players bet
    public void setBet(){
        System.out.println("You have " + playerMoney + "$\nHow much would you like to bet?");
      //  GameText.setText(GameText.getText() + "\nYou have " + playerMoney + "$\nHow much would you like to bet?");
        PlayerMoney.setText(""+playerMoney+"$");

        EnterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    playerBet = Integer.parseInt(PlayerInput.getText().toString());
                    EnterBtn.setEnabled(false);
                    if (PlayerInput.length() > 0) {
                        GameText.setText(GameText.getText() + "\nYour bet is " + playerBet);
                        if (playerBet > playerMoney) {
                            System.out.println("You haven't got enough money. You've been kicked out");

                            GameText.setText(GameText.getText() + "\nYou haven't got enough money. You've been kicked out");
                            WinLosePush.setText("You haven't got enough money. You've been kicked out");
                            GameText.setText(GameText.getText() + "\nWould you like to play again?\n(1)YES/(2)NO");
                            stand.setEnabled(false);
                            hit.setEnabled(false);
                            play.setEnabled(true);
                        } else {
                            PLAY();
                        }

                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                    playerBet = 0;
                }
            }
        });
    }

    public void PLAY(){
        YourPoints.setText("");
        DealerPoints.setText("");
        WinLosePush.setText("");
        imageView1.setBackgroundResource(R.drawable.colorgreen);
        imageView2.setBackgroundResource(R.drawable.colorgreen);
        imageView3.setBackgroundResource(R.drawable.colorgreen);
        imageView4.setBackgroundResource(R.drawable.colorgreen);
        imageView5.setBackgroundResource(R.drawable.colorgreen);
        imageView6.setBackgroundResource(R.drawable.colorgreen);
        imageView7.setBackgroundResource(R.drawable.colorgreen);
        imageView8.setBackgroundResource(R.drawable.colorgreen);
        imageView9.setBackgroundResource(R.drawable.colorgreen);
        playingDeck.createFullDeck();
        playingDeck.shuffle();

        playerDeck.draw(playingDeck);
        playerDeck.draw(playingDeck);

        //Dealer gets two cards
        dealerDeck.draw(playingDeck);
        dealerDeck.draw(playingDeck);

        //add card view here

                ViewCard(playerDeck, 0, imageView1);
                ViewCard(playerDeck, 1, imageView2);
                cardNumber = 1;

                ViewCard(dealerDeck,0,imageView7);
                imageView8.setBackgroundResource(R.drawable.tyl);


        System.out.println("Your hand:");
        System.out.println(playerDeck.toString());
      //  GameText.setText(GameText.getText() + "\nYour hand:\n"+playerDeck.toString());
        System.out.println("Your deck is valued at: " + playerDeck.cardsValue());
        GameText.setText(GameText.getText() + "\nYour deck is valued at:\n"+playerDeck.cardsValue());
        playerValue = playerDeck.cardsValue();
        YourPoints.setText(playerValue+"");


        if(playerDeck.deckSize() == 2 && playerValue == 21){
            System.out.println("BlackJack");
         //   GameText.setText(GameText.getText() + "\nBLACKJACK\nYou won");
            WinLosePush.setText("Blackjack. You won");
            playerMoney += playerBet;
            playerDeck.moveAllToDeck(playingDeck);
            dealerDeck.moveAllToDeck(playingDeck);
            System.out.println("End of hand.");
            GameText.setText(GameText.getText() + "\nEnd of hand");
            GameText.setText(GameText.getText() + "\nWould you like to play again?\nYES/NO?");
            stand.setEnabled(false);
            hit.setEnabled(false);
            play.setEnabled(true);

        }

        GameText.setText(GameText.getText() + "\nDealer hand:\n"+dealerDeck.getCard(0).toString() + " and [HIDDEN]");

        GameText.setText(GameText.getText() + "\nWould you like to HIT or Stand?");
        hit.setEnabled(true);
        stand.setEnabled(true);

    }

    public void HIT(View view) {
        cardNumber = cardNumber + 1;
        ImageView imageView = null;
        if(cardNumber == 2){
            imageView = imageView3;
        }
        else if(cardNumber == 3){
            imageView = imageView4;
        }
        else if(cardNumber == 4){
            imageView = imageView5;
        }
        else if(cardNumber == 5){
            imageView = imageView6;
        }
        playerDeck.draw(playingDeck);
        ViewCard(playerDeck, cardNumber, imageView);

        System.out.println("You draw a: " + playerDeck.getCard(playerDeck.deckSize() - 1).toString());
      //  GameText.setText(GameText.getText() + "\nYou draw a: "+playerDeck.getCard(playerDeck.deckSize() - 1).toString());
        System.out.println("Currently valued at: "+playerDeck.cardsValue());
        GameText.setText(GameText.getText() + "\nCurrently valued at: "+playerDeck.cardsValue());
        playerValue = playerDeck.cardsValue();
        YourPoints.setText(playerValue+"");
        //Bust if over 21
        if (playerDeck.cardsValue() > 21) {
            System.out.println("Bust. Currently valued at: " + playerDeck.cardsValue());
            System.out.println("Dealer wins");
            GameText.setText(GameText.getText() + "\nBust.Currently valued at: " + playerDeck.cardsValue() + "\nDealer wins");
            YourPoints.setText(playerValue+"");
            //winlosepush
            WinLosePush.setText("Bust.Dealer wins");

            playerDeck.moveAllToDeck(playingDeck);
            dealerDeck.moveAllToDeck(playingDeck);
            System.out.println("End of hand.");
            GameText.setText(GameText.getText() + "\nEnd of hand");
            GameText.setText(GameText.getText() + "\nWould you like to play again?\nYES/NO?");

            stand.setEnabled(false);
            hit.setEnabled(false);
            play.setEnabled(true);

            playerMoney -= playerBet;

            endRound = true;
        }
        else if(playerDeck.cardsValue() == 21){
            GameText.setText(GameText.getText() + "\nYou can only STAND now");
            hit.setEnabled(false);
        }
        else {
            GameText.setText(GameText.getText() + "\nWould you like to HIT or Stand?");
        }
    }

    public void STAND(View view) {
        int dealerCardNumber = 1;

            while (true) {
                if (dealerDeck.cardsValue() <= 16) {
                    System.out.println("Dealer is drawing...");
                    GameText.setText(GameText.getText() + "\nDealer is drawing...");
                    dealerDeck.draw(playerDeck);
                    dealerCardNumber = dealerCardNumber+1;
                    break;
                } else {
                    break;
                }
            }



            System.out.println("Dealer cards: " + dealerDeck.toString());
            GameText.setText(GameText.getText() + "\nDealer cards: " + dealerDeck.toString());
            System.out.println("Your cards value: " + playerValue);
            YourPoints.setText(playerValue+"");
            GameText.setText(GameText.getText() + "\nDealer is drawing...");


            System.out.println("Dealer cards value: " + dealerDeck.cardsValue());
            DealerPoints.setText(dealerDeck.cardsValue()+"");
            GameText.setText(GameText.getText() + "\nDealer cards value: "+dealerDeck.cardsValue());
        System.out.println("dealer card number ="+dealerCardNumber);

            for(int i = 1;i<=dealerCardNumber;i++){
               switch(i) {
                   case 1:
                   ViewCard(dealerDeck, 1, imageView8);
                   break;

                   case 2:
                   ViewCard(dealerDeck, 2, imageView9);
                   break;
                   case 3:
                       ViewCard(dealerDeck, 3, imageView10);
                       break;
                   case 4:
                       ViewCard(dealerDeck, 4, imageView11);
                       break;


               }

            }

        if(playerDeck.cardsValue() == 21 && playerDeck.deckSize() == 2){
            WinLosePush.setText("BLACKJACK.YOU WON");
        }

        if (dealerDeck.cardsValue() > playerValue && dealerDeck.cardsValue() <= 21) {
            System.out.println("You lost.");
          //  GameText.setText(GameText.getText() + "\nYou lost");
            WinLosePush.setText("You lost");

            playerMoney -= playerBet;
            endRound = true;
        }

        if (dealerDeck.cardsValue() > 21) {
            System.out.println("Dealer bust. You won");
            WinLosePush.setText("Dealer is busted. You won");

         //   GameText.setText(GameText.getText() + "\nDealer busted.You won");
            playerMoney += playerBet;
            endRound = true;
        }
        if (dealerDeck.cardsValue() < playerValue) {
            System.out.println("You won");
          //  GameText.setText(GameText.getText() + "\nYou won");
            WinLosePush.setText("You won");

            playerMoney += playerBet;
            endRound = true;
        }
        if (dealerDeck.cardsValue() == playerValue) {
            System.out.println("Push");
          //  GameText.setText(GameText.getText() + "\nPush");
            WinLosePush.setText("Push");

            endRound = true;
        }

        playerDeck.moveAllToDeck(playingDeck);
        dealerDeck.moveAllToDeck(playingDeck);
        System.out.println("End of hand.");
      //  GameText.setText(GameText.getText() + "\nEnd of hand");
        hit.setEnabled(false);
        stand.setEnabled(false);

        if (playerMoney > 0) {
        //    GameText.setText(GameText.getText() + "\nWould you like to play again?\nYES/NO?");

            hit.setEnabled(false);
            stand.setEnabled(false);
            play.setEnabled(true);

        }
        if(playerMoney == 0){
           // GameText.setText(GameText.getText() + "\nYou lost all your money :( Try again later");
            WinLosePush.setText("You lost all your money :( Try again later");
            hit.setEnabled(false);
            stand.setEnabled(false);
            play.setEnabled(false);

        }
    }

    public void PLAY_AGAIN(View view) {
        play.setEnabled(false);
        WinLosePush.setText("");
        DealerPoints.setText("");
        YourPoints.setText("");
        PlayerMoney.setText(playerMoney+"");
        imageView1.setBackgroundResource(R.drawable.colorgreen);
        imageView2.setBackgroundResource(R.drawable.colorgreen);
        imageView3.setBackgroundResource(R.drawable.colorgreen);
        imageView4.setBackgroundResource(R.drawable.colorgreen);
        imageView5.setBackgroundResource(R.drawable.colorgreen);
        imageView6.setBackgroundResource(R.drawable.colorgreen);
        imageView7.setBackgroundResource(R.drawable.colorgreen);
        imageView8.setBackgroundResource(R.drawable.colorgreen);
        imageView9.setBackgroundResource(R.drawable.colorgreen);

        onStart();
    }

    public void LEAVE(View view) {
     //   GameText.setText("So you want to leave,okey...");
        WinLosePush.setText("So you want to leave,okey...");
        playerDeck.moveAllToDeck(playingDeck);
        dealerDeck.moveAllToDeck(playingDeck);
        System.out.println("End of hand.");
        GameText.setText(GameText.getText() + "\nEnd of hand");
        if(playerMoney < 300)
        {
            GameText.setText(GameText.getText() + "\nYou lost: "+(300-playerMoney)+"$" );
            hit.setEnabled(false);
            stand.setEnabled(false);
            EnterBtn.setEnabled(false);
        }
        else if(playerMoney >= 300){
            GameText.setText(GameText.getText() + "\nCongratulation,you won: "+(playerMoney - 300)+"$");

            hit.setEnabled(false);
            stand.setEnabled(false);
            EnterBtn.setEnabled(false);
        }
        playerMoney = 300;
        play.setEnabled(true);
    }


    public void ViewCard(Deck playerDeck,int g,ImageView imageView){
         Deck cards;
         int i = g;


        cards = playerDeck;


                    Card card = cards.getCard(i);
                switch (card.getValue()){
                    case ACE:
                    switch (card.getSuit()){
                        case HEART:
                            imageView.setBackgroundResource(R.drawable.ace1);
                            break;
                        case CLUB:
                            imageView.setBackgroundResource(R.drawable.ace2);
                            break;
                        case SPADE:
                            imageView.setBackgroundResource(R.drawable.ace3);
                            break;
                        case DIAMOND:
                            imageView.setBackgroundResource(R.drawable.ace4);
                            break;

                    }
                    break;
                case KING:
                    switch (card.getSuit()){
                        case HEART:
                            imageView.setBackgroundResource(R.drawable.king1);
                            break;
                        case CLUB:
                            imageView.setBackgroundResource(R.drawable.king2);
                            break;
                        case SPADE:
                            imageView.setBackgroundResource(R.drawable.king3);
                            break;
                        case DIAMOND:
                            imageView.setBackgroundResource(R.drawable.king4);
                            break;


                    }
                    break;
                case QUEEN:
                    switch (card.getSuit()){
                        case HEART:
                            imageView.setBackgroundResource(R.drawable.queen1);
                            break;
                        case CLUB:
                            imageView.setBackgroundResource(R.drawable.queen1);
                            break;
                        case SPADE:
                            imageView.setBackgroundResource(R.drawable.queen1);
                            break;
                        case DIAMOND:
                            imageView.setBackgroundResource(R.drawable.queen1);
                            break;

                    }
                    break;
                case JACK:
                    switch (card.getSuit()){
                        case HEART:
                            imageView.setBackgroundResource(R.drawable.jack1);
                            break;
                        case CLUB:
                            imageView.setBackgroundResource(R.drawable.jack1);
                            break;
                        case SPADE:
                            imageView.setBackgroundResource(R.drawable.jack1);
                            break;
                        case DIAMOND:
                            imageView.setBackgroundResource(R.drawable.jack1);
                            break;

                    }
                    break;
                    case TEN:
                        switch (card.getSuit()){
                            case HEART:
                                imageView.setBackgroundResource(R.drawable.ten1);
                                break;
                            case CLUB:
                                imageView.setBackgroundResource(R.drawable.ten2);
                                break;
                            case SPADE:
                                imageView.setBackgroundResource(R.drawable.ten3);
                                break;
                            case DIAMOND:
                                imageView.setBackgroundResource(R.drawable.ten4);
                                break;
                        }
                        break;
                    case NINE:
                        switch (card.getSuit()){
                            case HEART:
                                imageView.setBackgroundResource(R.drawable.nine1);
                                break;
                            case CLUB:
                                imageView.setBackgroundResource(R.drawable.nine2);
                                break;
                            case SPADE:
                                imageView.setBackgroundResource(R.drawable.nine3);
                                break;
                            case DIAMOND:
                                imageView.setBackgroundResource(R.drawable.nine4);
                                break;
                        }
                        break;
                case EIGHT:
                    switch (card.getSuit()){
                        case HEART:
                            imageView.setBackgroundResource(R.drawable.eight1);
                            break;
                        case CLUB:
                            imageView.setBackgroundResource(R.drawable.eight2);
                            break;
                        case SPADE:
                            imageView.setBackgroundResource(R.drawable.eight3);
                            break;
                        case DIAMOND:
                            imageView.setBackgroundResource(R.drawable.eight4);
                            break;
                    }
                    break;
                case SEVEN:
                    switch (card.getSuit()){
                        case HEART:
                            imageView.setBackgroundResource(R.drawable.seven1);
                            break;
                        case CLUB:
                            imageView.setBackgroundResource(R.drawable.seven2);
                            break;
                        case SPADE:
                            imageView.setBackgroundResource(R.drawable.seven3);
                            break;
                        case DIAMOND:
                            imageView.setBackgroundResource(R.drawable.seven4);
                            break;
                    }
                    break;
                case SIX:
                    switch (card.getSuit()){
                        case HEART:
                            imageView.setBackgroundResource(R.drawable.six1);
                            break;
                        case CLUB:
                            imageView.setBackgroundResource(R.drawable.six2);
                            break;
                        case SPADE:
                            imageView.setBackgroundResource(R.drawable.six3);
                            break;
                        case DIAMOND:
                            imageView.setBackgroundResource(R.drawable.six4);
                            break;

                    }
                    break;
                case FIVE:
                    switch (card.getSuit()){
                        case HEART:
                            imageView.setBackgroundResource(R.drawable.five1);
                            break;
                        case CLUB:
                            imageView.setBackgroundResource(R.drawable.five2);
                            break;
                        case SPADE:
                            imageView.setBackgroundResource(R.drawable.five3);
                            break;
                        case DIAMOND:
                            imageView.setBackgroundResource(R.drawable.five4);
                            break;

                    }
                    break;
                case FOUR:
                    switch (card.getSuit()){
                        case HEART:
                            imageView.setBackgroundResource(R.drawable.four1);
                            break;
                        case CLUB:
                            imageView.setBackgroundResource(R.drawable.four2);
                            break;
                        case SPADE:
                            imageView.setBackgroundResource(R.drawable.four3);
                            break;
                        case DIAMOND:
                            imageView.setBackgroundResource(R.drawable.four4);
                            break;

                    }
                    break;
                case THREE:
                    switch (card.getSuit()){
                        case HEART:
                            imageView.setBackgroundResource(R.drawable.three1);
                            break;
                        case CLUB:
                            imageView.setBackgroundResource(R.drawable.three2);
                            break;
                        case SPADE:
                            imageView.setBackgroundResource(R.drawable.three3);
                            break;
                        case DIAMOND:
                            imageView.setBackgroundResource(R.drawable.three4);
                            break;

                    }
                    break;
                case TWO:
                    switch (card.getSuit()){
                        case HEART:
                            imageView.setBackgroundResource(R.drawable.two1);
                            break;
                        case CLUB:
                            imageView.setBackgroundResource(R.drawable.two2);
                            break;
                        case SPADE:
                            imageView.setBackgroundResource(R.drawable.two3);
                            break;
                        case DIAMOND:
                            imageView.setBackgroundResource(R.drawable.two4);
                            break;

                    }
            }

    }

}