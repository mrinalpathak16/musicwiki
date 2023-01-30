
# MusicWiki

MusicWiki is a very simple, unofficial Last.fm app that contains information about different music genres, the albums, artists and tracks listed under the genre.

The application has been written completely in Kotlin, follows the recommended MVVM architecture and makes use of the Android Navigation Component for in-app navigation.
It uses Retrofit to make network API requests, Glide to load images from the Web into their respective ImageViews. ViewBinding has also been used for referencing different views in the layout xml files, for clean design. Tabs have been implemented using the ViewPager2 library. All the lists and grids in the app have been implemented using RecyclerView.

Some features which might be added in future are pagination to dynamically load more data in RecyclerViews with swipes, and an option to locally persist some data such as favourite albums, artist or tracks.


## Acknowledgements
All of the required information for building this app is available in the API provided by
last.fm.

 - [Last.fm API](https://www.last.fm/api)


## Tech Stack

Kotlin, Android Navigation Components, ViewBinding, MVVM Architecture, Kotlin Coroutines, LiveData, Retrofit, Glide, RecyclerView, TabLayout, ViewPager2.


## Screenshots

![App Screenshot 1](https://drive.google.com/uc?export=view&id=10UOs4_x1QLwWO6G8M-HlUOnyvFSXEbjr)

**Default First Screen**

![App Screenshot 2](https://drive.google.com/uc?export=view&id=1784c1q1iq4hktJU8Dmf41Wro5YzHeHC3)

**Expanded First Screen**

![App Screenshot 3](https://drive.google.com/uc?export=view&id=1zNZWcq7_8IzAyjL8NsrcQyfD2AJJ2Qlt)

**Genre Info - Albums Tab**

![App Screenshot 4](https://drive.google.com/uc?export=view&id=1bOUC7-N4VDWZdOfvykx-XjvtRRWVoL1C)

**Genre Info - Artists Tab**

![App Screenshot 5](https://drive.google.com/uc?export=view&id=1KCfj5j8ifyEBDUXGFi2BNc0XaNCsr3JP)

**Genre Info - Tracks Tab**

![App Screenshot 6](https://drive.google.com/uc?export=view&id=1DHRYFaP03W0WSLwrjXJ89SQ2Y1xL4eNF)

**Album Info**

![App Screenshot 7](https://drive.google.com/uc?export=view&id=1hvMDzMah6ZbTZDpKtZCbrdUloVK-omu1)

**Artist Info**

