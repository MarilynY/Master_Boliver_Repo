## Project Motivation:
* ###### Compare and contrast with other similar service, to demonstrate our unique? accomplishments
* insert
* Inspired by drone delivery projects such as Google's X-wing and Amazon's Prime Air,
  our company seeks to provide a robot delivery service in San Francisco.

## Impact:
* ###### Quantify accomplishments, make it specific
* insert
* We have three bases located in San Francisco, each covers a different area, aiming to reach most neighborhoods.
* We will make local short distance delivery available in a convenient and cheap fashion, while not adding any more traffic to the roads.

## 如何根据需求设计这个项目？

We started out with listing all the requirements of the project, the scenario, drew a complete user journey,
and defined the minimum core services that we need to realize.

 `Insert logistics_app txt to show initial design document`

Since we don't actually have any robots, we manipulated the database and used two GoogleAPIs to show case
how our backend logic and frontend would realize this logistic service.
> In short, the user journey starts with getting information such as time and cost for a potential delivery order.
  Our logic is, find the closest bases, which have drone and ground robot,
  to the pick-up location provided by the user, and calculate the time and cost for both drone and ground robot to
  get from the base to the pick up location, then to the destination and return this information to the user.
  So user only need to provide a pick up location and a destination. User has two options, either to choose drone
  or ground robot to handle the delivery. Then, the user can submit his/her/they selection.
  A new order will be created and the first available robot in the base closest to the pick up location will be assigned
  to handle that delivery job. Once an order is submitted, user can view the order in `Track`, which displays all the current orders,
  each current order provides a track function, which shows a progress bar of the status of the order, such as `being retrieved`,
  or `being delivered` or `arrived`. User can `cancel` an order only before the robot picks it up, and a user can `confirm`
  an order once it has `arrived`. Both `cancelled` and `confirmed` orders will be moved from `Trck` to `History Order`, as they
  are no longer on going orders. `History Order` provide a list of all historyOrder of a user with most relevant information of each order.
  Both `CurrentOrders` and `HistoryOrders` are `sorted` in `descending` order for display. Current orders are sorted by `create time`, while HistoryOrder
  are sorted by `actual arrival time`.

## 如何多角色分工合作，如何项目管理？
  `Trello`
  > Trello was like a bulletin board,
    we put which jobs are assigned to which person on the board
    and update it every time someone finishes a job,
    we have columns like TODO and DONE

  `SourceTree`
  > We used Source Tree as a local UI for managing versions and interacting with github.
    Xinyi is the master repo, everyone else fork his repo, and pull it to local for development
    then we sycn our own repo and Xinyi merge each one's repo to a dev branch of his master repo,
    conflicts are solved manually when they occur. When others start developing,
    they would get the latest version from Xinyi and then develop locally"

  `Github`
  > Xinyi is the master repository of our project, everyone forked his and developed locally.
    Everytime after anyone finishes developing something at the end of a day,
    they sycn their local files to their own github, and Xinyi will merge them into his dev branch of the master repo

  `Discord`
  > Created a discord server with multiple voice channels
    so people can talk to their own sub-group when working on functions that are
    closely linked. Well, at least that was the plan. At the end of the day, it was only the active ones that used it.
    Multiple text channels were also created and act as a storage for file sharing

  `Zoom`
  > Used Zoom at first for group meetings,
    though as soon as we realize free accounts only support a short period of time,
    we switched to Google Hangout. However, towards the end, we found out if only two people are in a room,
    zoom won't kick them out for a long period of time, it remains unknown if they will ever be kicked out if only two people are in a   room

  `Google Hangout`
  > Google Hangout is almost the perfect solution for group meeting, not only we can share screen,
    we can all share screen at the same time and we can easily switch between whose screen to watch.
    On top of everything, It is FREE!

  `Wechat`
  > When we are off doing our own things, we communicated a lot on wechat,
    from debugging to resource sharing, to brainstorming ideas, and if words cannot explain something,
    we just hop on discord or google hangout or a 1 v 1 zoom meeting
    depending on if we need to share screen and how many of us need to be there

## 遇到的挑战以及如何解决？

* `CORS`
> We read a lot on Cross Origin Resource Sharing, the why and the what behind it, and those of Same Origin Policy. We understood the concept
  of pre-flight, but we didn't know that it came in the form of `Method: OPTIONS`. So we struggled a lot on trying to get pass `CORS`.
  During dev, we simply used CORS chrome extension to disable it, so we can test our api when interacting with our backend. Towards the end, and
  just a day before this document is written, we finally solved CORS after realzing the OPTIONS is the pre-flight check, and we needed to
  handle OPTIONS so that the actual request from our front end would be sent over.

* `Does not actuallly have robots`
>  We used two GoogleAPIs, `DistanceMatrix` and `GeoEncoding`. insert why use them and what they are and how we used them...
* `Merge Conflict`
>
* `Maven deployment`
>  Had three problems during deployment of our maven project
   * First, I didn't know that the project name in the pom file needs to be the same as the built and exported war file
     our deployed war file could not connect to database due to difference in name.war and that in pom file
   * Second, the UpperLower case of the name of tables in our mysql base did not match the name we used in our codes,
     this also caused failure in trying to access tables
   * Third, these would not have been such a pain if we knew how to read logs on tomcat server. Only a night later did I find the way to see the logs,
     I needed to find where the log is and how to access it since normal access were denied due to my default permission
* `Debug during integration`
> A lot of changes were made during integration, stemming from different format of the same data, bad names, UpperLower case of names, identifying sources of
  NullPointerExceptions, failuers in passing data along, and identifying whether it is frontend or the backend that produced the error
* `Version control / Collaboration`
>
* `React / Dataflow`
> * Everyone in our group was new to react, so we were coding and learning along the way. We divided the front end development into
  three big components, SearchRoutes / SubmitOrder, getCurreOrders / Track / Confirm / Cancel, and getHistoryOrders. Biggest problem was dataflow,
  since we were developing locally and individually, we didn't have a good dataflow design untill everyone finishe and we re-structured our codes to
  ensure a better dataflow, such as storing shared data on the lowest common ancestor and pass data down to each child that needs it, and re-render is enabled
  since once the parent receives new data, all its children will be re-rendered with the new data that were passed down
  * Another challenge is probably getting into thinking in components instead of traditional html files, javascripts and css styles.
  For example, I may need only one component to realize the display of many different sets of data. Instead of writing a list of things, I may only need one list item
  to dispaly all list items. Given the different data, that one list item is used to render all list items. Just changing the content, but not its appearnce/outfit.

* `What if all robots are busy/unavailable`
> At the moment, we just tell users to try again later. However, this is definitely not ideal becuase we would never want
  our service to appear as unavailable. We could implement a queue and when all robots are busy, instead of telling customers to
  try again later, we can place them in the queue and calculate a waiting time and tell them that they will need to wait for an estimated
  time before a robot is available to handle their delivery. To calcualte this estimated time, one way to solve it is to record an estimated avaiable
  time for each robot on mission, and sort this list so that we know of all the robots on mission, who could be the earliest to return and how long that is,
  so we can add this time and perhaps some specific time, which is needed for the robot to be recharged and ready for a new assignment,
  and tell user that this is the estimated time before a robot can handle it, so we never appear `unavailable`, and our services
  is always `available`. Only sometimes it will take longer to fullfill a delivery order

  ## Basic Architecture:
  * #### Authentication
   * ##### Log in
     > verify username & password -> create token -> return token to front end
   * ##### Log out
     > add user's token to blacklist -> return user to login page
   * ##### Register
     > check if token exists -> if token does not exist -> let user register -> else tell user to log out first before able to register

  * #### Order
   * ##### Get recommended delivery option
     > user input pick-up location and destination -> find closest bases with available ground bot and drone -> calculate time, distance, and cost for both robots to travel from base to pick-up location and to destination
     -> return to user with two delivery options, the time and money costs for drone and those for ground bot
   * ##### Submit order
   > if user choses one of the two options returned above, user inputs a few more information such as the name of sender and that of receiver,
     -> submits Order -> a new order is created -> the selected type of robot will be located at the closest available base and assigned to execute this order,
     -> user will be notified if order is successfully created and robot is on its way to pick up package
   * ##### Cancel Order
   > by giving an orderId, user can cancel a current order only if package has not yet been picked up by robot,
     -> the status of this order will be set to "canceled", and moved from currentOrder to historyOrder,
     -> the assigned robot will be released of duty and return to closest base
   * ##### Track order
   > user can provide an orderId to get all the information on the corresponding order,
     such as its current location and progress status such as "being retrieved" or "being delivered"
   * ##### Confirm Order
   > once robot reached its destination, user can confirm the order -> it will set the order status to be "completed",
     and this order will be moved from currentOrder to historyOrder -> the assigned robot will return to closest base
   * ##### Get all current orders
   > user can browse all of their current orders, current as in any ongoing order that is not yet confirmed to be completed or canceled
   * ##### Get all history orders
   > user can browse all of their past orders, past as in any order with status "completed" or "canceled"

## 最终产品的demo
http://boliver-frontend.s3-website-us-west-1.amazonaws.com/home
