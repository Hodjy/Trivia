# Android Trivia Project        
![OpeningScreen](https://user-images.githubusercontent.com/62711261/127738972-6f018e8d-2e76-484b-9b2c-ae12d234b932.png)       

An interactive educational trivia app about nature.         
The app uses Fragments, Animations, Glide library, RecyclerView, and more.          

## Difficulty       
![Difficulty](https://user-images.githubusercontent.com/62711261/127739010-d0a7c834-637d-4c20-8e6e-98192d90f62f.png)        

After pressing play, a fragment will be replaced with the difficulty fragment.        
Picking a difficulty will start a new game, while pressing either the "Back" or the phone backspace will return to the previous fragment.       

## Game       
![Game](https://user-images.githubusercontent.com/62711261/127739073-3ce5b1d1-1196-4d1e-be81-ed85ed526966.png)

The game screen, the player has 3 lives and occumilate scores when answering correctly.         
Difficulty affects the score (E-100,M-200,H-300).           

## GameOver       
![GameOver](https://user-images.githubusercontent.com/62711261/127738976-f78702c0-99b6-48bd-b4d2-2043d0b586a9.png)        

After the game is finished, this screen is displayed.         
The player can save his score, return to the home screen, go the leaderboards,and retry the game on the same difficulty.        

## Leaderboards         
![Leaderboard](https://user-images.githubusercontent.com/62711261/127738980-cc4d27b5-a99e-42c0-9b5e-1a1a3b462184.png)       

All saved scores are shown here.        
The game will save only the best 20 scores, and will sort them from best to worse.        
The scores are saved to a flie, and not by SharedPreferences.       
The leaderboard uses ListView.       

## Encyclopedia       
![Encyclopedia](https://user-images.githubusercontent.com/62711261/127738984-60f8be4a-2297-497d-93d3-c8e4c2670638.png)          

The player can always check the foliage database by entering the Encyclopedia.        
Learn and memorise the varying foliage in order to have a stronger knowledge about them!        
After all, the trivia is for the player to learn in a interactive way.        
The encyclopedia uses RecyclerView with Glide library.



