# Tech News Today

![rsz_app_icon](https://user-images.githubusercontent.com/16197563/43371167-09233522-9395-11e8-9c55-ced271816240.png)

Tech News Today App is the Capstone Project and created as a part of [Udacity Android Developer Nanodegree Program](https://www.udacity.com/course/android-developer-nanodegree-by-google--nd801).

## Description
Tech News Today is a news app that allows users to read the most famous Tech News blogs and sites,
share news with their friends and save their favourite articles.

If you want to stay up to date with what happens in the world of Technology, Hardware, smartphones,
newest games and apps, updates and reviews, latest geek stories and use as little time as possible, then
Tech News Today is what you need!

You can read news from these 10 sources:
* Ars Technica
* Engadget
* Gründerszene
* Hacker News 
* Re/Code 
* t3n digital pioneers
* TechCrunch 
* Techradar
* The Next Web
* The Verge  

## The main features of the app are:
* User can choose among 10 different sources
* Ability to bookmark favorite articles to view them even when offline 
* Ability to share articles
* Webview displays the Full Article
* Google Ads under each article
* Widget to display all the favorite articles
* Google Analytics for screen tracking  

## Instalation 
The app is written in the Java Programming Language and uses only stable release versions of all libraries, Gradle and Android Studio.

The target SDK is 27 and the minimum SDK is 19.

The app uses the [News API](https://newsapi.org) to get news information from different sources. You must provide your own API key in order to build the app. When you obtain API key, replace api_key with your API key in the ~/.gradle/gradle.properties file.
```
buildTypes.each {
        it.buildConfigField 'String', 'API_KEY', api_key
}
```
## Screenshots
![webp net-resizeimage](https://user-images.githubusercontent.com/16197563/43658489-b8c102dc-9761-11e8-933d-45c776188afa.png)![webp net-resizeimage](https://user-images.githubusercontent.com/16197563/43658575-fadfcb94-9761-11e8-8e77-f2d5859c6de4.png)![webp net-resizeimage](https://user-images.githubusercontent.com/16197563/43658635-2b7debe6-9762-11e8-8c82-334f559e6564.png)
![webp net-resizeimage](https://user-images.githubusercontent.com/16197563/43658729-59c1498a-9762-11e8-8264-6f21477e7654.png)![webp net-resizeimage](https://user-images.githubusercontent.com/16197563/43658809-95218e22-9762-11e8-8abf-0fc181006903.png)![webp net-resizeimage](https://user-images.githubusercontent.com/16197563/43658896-d9e5e080-9762-11e8-9111-bea395e75757.png)

## Libraries
* [Picasso](https://github.com/square/picasso)
* [ButterKnife](https://github.com/JakeWharton/butterknife)
* [Retrofit](https://github.com/square/retrofit)

## Icon credits
* Smartphone Icon by [Freepik](https://www.flaticon.com/authors/freepik)
* Favourite Button Icon by [Freepik](https://www.flaticon.com/authors/freepik)
* Cross/Delete Button Icon by [Freepik](https://www.flaticon.com/authors/freepik)
* Heart Button Icon by [Freepik](https://www.flaticon.com/authors/freepik)
* Share Button Icon by [Freepik](https://www.flaticon.com/authors/freepik)
