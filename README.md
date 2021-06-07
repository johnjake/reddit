# Reddit Client ![alt text](https://github.com/johnjake/reddit/blob/master/reddit_icon.png)

This app is about listing of reddit client post, reddit topics (subreddit). 

For the Reddit Client development the following IDE and patterns are used: 


    IDE: Android Studio Artic Fox 2020.3.1 Beta 3
    Runtime Version: 11.8.0 (jre)
    VM: OpenJDK 64-Bit

    Gradle Version: 7.0.0-beta03
    Kotlin Version: 1.4.31
    Gradle Type: DLS

    Language: Kotlin,
              XML

    Architechture Pattern: MVVM
    
    3rd Party
    Dependency Injection: Koin-2.0.1
    Http Client: OkHttp-4.9.0
    Http Client: Retrofit-2.9.0
    Concurrency Library: Coroutine-1.4.2
    Reactive Extensions: RxJava2 (2.2.10)
    RecyclerView Paging: Paging3 (3.0.0)
    Navigation Component: 2.3.5
    Animation: lottie:3.4.4
               MotionLayout
    
    Room: 2.3.0-alpha03
    Unit Test: koin-test:2.0.1
    
  
   API Credential:
   
    username: johnjake2000
    password: livelove_2000
    api url: www.reddit.com/dev/api
    
    
USER API: 
         
         * The subreddit and post list can be obtained at using this endpoint: https://www.reddit.com/dev/api. 

FEATURES: 
          
          * List all subreddit (categories)
          * Search subreddit (categories)
          * List of all post in specific subreddit (category)
          * List number of comments and upvotes in each subreddit (categories)
          * Subreddit mark as less than 5 upvotes or greater than 5 upvotes (Great) and zero upvotes for no upvotes.
          * Open webview for specific subreddit.
          * Integrating pagination
          
SETBACK (Need to improved): 
       
         * Unable to get reddit token as it requires time to fixed, so uses local images to simulate the UI/UX. 
         * Unable to comply some requirements like mark high comment or upvotes since it uses pagination, and requires time to integrate the features.
         * It uses deprecated paging PagedListAdapter due to reddit end points does not provide pagination number, instead the pagination are query by limit item. 

   User Interface:
   
  ![alt text](https://github.com/johnjake/reddit/blob/master/reddit.png)



