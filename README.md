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
![rsz_screenshot_1532599580](https://user-images.githubusercontent.com/16197563/43371235-5051a8ec-9396-11e8-8af7-ab5ce6de4e53.png) ![rsz_screenshot_1532880364](https://user-images.githubusercontent.com/16197563/43371239-5d0f0598-9396-11e8-87ce-0423908f1944.png) ![rsz_screenshot_1532880280](https://user-images.githubusercontent.com/16197563/43371247-8cec5db0-9396-11e8-9c48-fd94a4b0fb24.png)
![rsz_screenshot_1532880325](https://user-images.githubusercontent.com/16197563/43371261-c1ddf45c-9396-11e8-94ba-3f6c72d9bd0d.png) ![rsz_screenshot_1532946926](https://user-images.githubusercontent.com/16197563/43392867-a5ab821a-93fd-11e8-8552-4397e7fad581.png) ![rsz_screenshot_1532598925](https://user-images.githubusercontent.com/16197563/43371408-0bc01b06-939a-11e8-8094-2ead0d1e6c65.png)


## Libraries
* [Picasso](https://github.com/square/picasso)
* [ButterKnife](https://github.com/JakeWharton/butterknife)
* [Retrofit](https://github.com/square/retrofit)

## Icon credits
* Smartphone Icon by [Freepik](https://www.flaticon.com/authors/freepik)
* Favourite Button Icon by [Freepik](https://www.flaticon.com/authors/freepik)
* Trash Button Icon by [Freepik](https://www.flaticon.com/authors/freepik)
* Heart Button Icon by [Freepik](https://www.flaticon.com/authors/freepik)
* Share Button Icon by [Freepik](https://www.flaticon.com/authors/freepik)
