package com.kylix.submissionbajp3.util

import com.kylix.submissionbajp3.model.local.entity.MovieModel
import com.kylix.submissionbajp3.model.local.entity.TvShowModel

object DummyData {
    fun getMovie(): List<MovieModel> {
        val movies: ArrayList<MovieModel> = ArrayList()
        movies.apply {
            add(
                MovieModel(
                    1,
                    "Alita: Battle Angel",
                    "When Alita awakens with no memory of who she is in a future " +
                        "world she does not recognize, she is taken in by Ido, a compassionate doctor " +
                        "who realizes that somewhere in this abandoned cyborg shell is the heart and" +
                        " soul of a young woman with an extraordinary past.",
                    "14/02/2019",
                    "Action, Science Fiction, Adventure",
                    "2 hours 2 minutes",
                    "71%",
                    "movie_alita"
                )
            )

            add(
                MovieModel(
                    2,
                    "Aquaman",
                    "Once home to the most advanced civilization on Earth, Atlantis is now an " +
                        "underwater kingdom ruled by the power-hungry King Orm. With a vast army at " +
                        "his disposal, Orm plans to conquer the remaining oceanic people and then " +
                        "the surface world. Standing in his way is Arthur Curry, Orms half-human, " +
                        "half-Atlantean brother and true heir to the throne.",
                    "21/12/2018",
                    "Action, Adventure, Fantasy",
                    "2 hours 24 minutes",
                    "69%",
                    "movie_aquaman"
                )
            )

            add(
                MovieModel(
                    3,
                    "Bohemian Rhapsody",
                    "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass " +
                        "guitarist John Deacon take the music world by storm when they form the rock " +
                        " and roll band Queen in 1970. Hit songs become instant classics. When Mercury" +
                        " s increasingly wild lifestyle starts to spiral out of control, Queen soon " +
                        "faces its greatest challenge yet – finding a way to keep the band together " +
                        "amid the success and excess.",
                    "02/11/2018",
                    "Drama, Music",
                    "2 hours 15 minutes",
                    "80%",
                    "movie_bohemian"
                )
            )

            add(
                MovieModel(
                    4,
                    "Fantastic Beasts: The Crimes of Grindelwald",
                    "Gellert Grindelwald has escaped imprisonment and has begun gathering " +
                        "followers to his cause—elevating wizards above all non-magical beings. The " +
                        "only one capable of putting a stop to him is the wizard he once called his" +
                        " closest friend, Albus Dumbledore. However, Dumbledore will need to seek" +
                        "help from the wizard who had thwarted Grindelwald once before, his former " +
                        "student Newt Scamander, who agrees to help, unaware of the dangers that lie " +
                        "ahead. Lines are drawn as love and loyalty are tested, even among the truest" +
                        " friends and family, in an increasingly divided wizarding world.",
                    "16/11/2018",
                    "Adventure, Fantasy, Drama",
                    "2 hours 14 minutes",
                    "69%",
                    "movie_crimes"
                )
            )

            add(
                MovieModel(
                    5,
                    "Glass",
                    "In a series of escalating encounters, former security guard David " +
                        "Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed" +
                        " man who has twenty-four personalities. Meanwhile, the shadowy presence of " +
                        "Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                    "18/01/2019",
                    "Thriller, Drama, Science Fiction",
                    "2 hours 9 minutes",
                    "66%",
                    "movie_glass"
                )
            )

            add(
                MovieModel(
                    6,
                    "How to Train Your Dragon: The Hidden World",
                    "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ " +
                        "discovery of an untamed, elusive mate draws the Night Fury away. When danger" +
                        " mounts at home and Hiccup’s reign as village chief is tested, both dragon " +
                        "and rider must make impossible decisions to save their kind.",
                    "22/02/2019",
                    "Animation, Family, Adventure",
                    "1 hour 44 minutes",
                    "78%",
                    "movie_how_to_train"
                )
            )

            add(
                MovieModel(
                    7,
                    "Avengers: Infinity War",
                    "As the Avengers and their allies have continued to protect the world from " +
                        "threats too large for any one hero to handle, a new danger has emerged from" +
                        " the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is " +
                        "to collect all six Infinity Stones, artifacts of unimaginable power, and " +
                        "use them to inflict his twisted will on all of reality. Everything the " +
                        "Avengers have fought for has led up to this moment - the fate of Earth and " +
                        "existence itself has never been more uncertain.",
                    "27/04/2018",
                    "Adventure, Action, Science Fiction",
                    "2 hours 29 minutes",
                    "83%",
                    "movie_infinity_war"
                )
            )

            add(
                MovieModel(
                    8,
                    "Overlord",
                    "France, June 1944. On the eve of D-Day, some American paratroopers fall " +
                        "behind enemy lines after their aircraft crashes while on a mission to destroy" +
                        " a radio tower in a small village near the beaches of Normandy. After reaching" +
                        " their target, the surviving paratroopers realise that, in addition to fighting " +
                        "the Nazi troops that patrol the village, they also must fight against something else.",
                    "9/11/2018",
                    "Horror, War, Science Fiction",
                    "1 hour 50 minutes",
                    "67%",
                    "movie_overlord"
                )
            )

            add(
                MovieModel(
                    9,
                    "Ralph Breaks the Internet",
                    "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz " +
                        "must risk it all by traveling to the World Wide Web in search of a replacement" +
                        " part to save Vanellope's video game, \"Sugar Rush.\" In way over their heads, " +
                        "Ralph and Vanellope rely on the citizens of the internet -- the netizens -- " +
                        "to help navigate their way, including an entrepreneur named Yesss, who is the " +
                        "head algorithm and the heart and soul of trend-making site BuzzzTube.\n",
                    "21/11/2018",
                    "Family, Animation, Comedy, Adventure",
                    "1 hour 52 minutes",
                    "72%",
                    "movie_ralph"
                )
            )

            add(
                MovieModel(
                    10,
                    "Spider-Man: Into the Spider-Verse",
                    "Miles Morales is juggling his life between being a high school" +
                        " student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super " +
                        "collider, others from across the Spider-Verse are transported to this dimension.",
                    "14/12/2018",
                    "Action, Adventure, Animation, Science Fiction, Comedy",
                    "1 hour 57 minutes",
                    "84%",
                    "movie_spiderman"
                )
            )
        }
        return movies
    }

    fun getTvShow(): List<TvShowModel> {
        val tvShows: ArrayList<TvShowModel> = ArrayList()
        tvShows.apply {
            add(
                TvShowModel(
                    1,
                    "Dragon Ball",
                    "Long ago in the mountains, a fighting master known as Gohan " +
                        "discovered a strange boy whom he named Goku. Gohan raised him and trained" +
                        " Goku in martial arts until he died. The young and very strong boy was on " +
                        "his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, " +
                        "whose search for the dragon balls brought her to Goku's home. Together, " +
                        "they set off to find all seven dragon balls in an adventure that would change" +
                        " Goku's life forever. See how Goku met his life long friends Bulma, Yamcha, " +
                        "Krillin, Master Roshi and more. And see his adventures as a boy, all leading " +
                        "up to Dragonball Z and later Dragonball GT.\n",
                    "02/26/1986",
                    "Comedy, Sci-Fi & Fantasy, Animation, Action & Adventure",
                    "25 minutes / episode",
                    "80%",
                    "tvshow_dragon_ball"
                )
            )

            add(
                TvShowModel(
                    2,
                    "Fairy Tail",
                    "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when" +
                        " visiting Harujion Town, she meets Natsu, a young man who gets sick easily" +
                        " by any type of transportation. But Natsu isnt just any ordinary kid, hes " +
                        "a member of one of the worlds most infamous mage guilds: Fairy Tail.",
                    "10/12/2009",
                    "Action & Adventure, Animation, Comedy, Sci-Fi & Fantasy",
                    "25 minutes / episode",
                    "75%",
                    "tvshow_fairytail"
                )
            )

            add(
                TvShowModel(
                    3,
                    "Family Guy",
                    "Sick, twisted, politically incorrect and Freakin Sweet animated series " +
                        "featuring the adventures of the dysfunctional Griffin family. Bumbling Peter" +
                        " and long-suffering Lois have three kids. Stewie (a brilliant but sadistic " +
                        "baby bent on killing his mother and taking over the world), Meg (the oldest," +
                        " and is the most unpopular girl in town) and Chris (the middle kid, he is not" +
                        " very bright but has a passion for movies). The final member of the family " +
                        "is Brian - a talking dog and much more than a pet, he keeps Stewie in check " +
                        "whilst sipping Martinis and sorting through his own life issues.",
                    "01/31/1999",
                    "Animation, Comedy",
                    "22 minutes / episode",
                    "68%",
                    "tvshow_family_guy"
                )
            )

            add(
                TvShowModel(
                    4,
                    "The Flash",
                    "After a particle accelerator causes a freak storm, CSI Investigator Barry " +
                        "Allen is struck by lightning and falls into a coma. Months later he awakens " +
                        "with the power of super speed, granting him the ability to move through Central " +
                        "City like an unseen guardian angel. Though initially excited by his newfound " +
                        "powers, Barry is shocked to discover he is not the only meta-human who " +
                        "was created in the wake of the accelerator explosion -- and not everyone is " +
                        "using their new powers for good. Barry partners with S.T.A.R. Labs and " +
                        "dedicates his life to protect the innocent. For now, only a few close friends " +
                        "and associates know that Barry is literally the fastest man alive, but it " +
                        "wont be long before the world learns what Barry Allen has become...The Flash.",
                    "10/07/2014",
                    "Drama, Sci-Fi & Fantasy",
                    "44 minutes / episode",
                    "75%",
                    "tvshow_flash"
                )
            )

            add(
                TvShowModel(
                    5,
                    " Marvels Iron Fist",
                    "Danny Rand resurfaces 15 years after being presumed dead. Now, with the " +
                        "power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny",
                    "03/17/2017",
                    "Action & Adventure, Drama, Sci-Fi & Fantasy",
                    "55 minutes / episode",
                    "64%",
                    "tvshow_iron_fist"
                )
            )

            add(
                TvShowModel(
                    6,
                    "Naruto Shippuden",
                    "Naruto Shippuuden is the continuation of the original animated TV series " +
                        "Naruto.The story revolves around an older and slightly more matured Uzumaki " +
                        "Naruto and his quest to save his friend Uchiha Sasuke from the grips of the " +
                        "snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally " +
                        "returns to his village of Konoha, and sets about putting his ambitions to " +
                        "work, though it will not be easy, as He has amassed a few (more dangerous) " +
                        "enemies, in the likes of the shinobi organization; Akatsuki.",
                    "02/15/2007",
                    "Animation, Comedy, Drama",
                    "25 minutes / episode",
                    "87%",
                    "tvshow_naruto_shipudden"
                )
            )

            add(
                TvShowModel(
                    7,
                    "NCIS",
                    "From murder and espionage to terrorism and stolen submarines, a team of " +
                        "special agents investigates any crime that has a shred of evidence connected " +
                        "to Navy and Marine Corps personnel, regardless of rank or position.",
                    "09/03/2003",
                    "Action & Adventure, Crime, Drama",
                    "45 minutes / episode",
                    "71%",
                    "tvshow_ncis"
                )
            )

            add(
                TvShowModel(
                    8,
                    "Supergirl",
                    "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family " +
                        "when she was 13 after being sent away from Krypton, must learn to embrace " +
                        "her powers after previously hiding them. The Danvers teach her to be careful" +
                        " with her powers, until she has to reveal them during an unexpected disaster, " +
                        "setting her on her journey of heroism.",
                    "10/25/2016",
                    "Action, Adventure, Drama, Science Fiction",
                    "42 minutes / episode",
                    "71%",
                    "tvshow_supergirl"
                )
            )

            add(
                TvShowModel(
                    9,
                    "Supernatural",
                    "When they were boys, Sam and Dean Winchester lost their mother to a " +
                        "mysterious and demonic supernatural force. Subsequently, their father raised " +
                        "them to be soldiers. He taught them about the paranormal evil that lives in " +
                        "the dark corners and on the back roads of America ... and he taught them " +
                        "how to kill it. Now, the Winchester brothers crisscross the country in their " +
                        "67 Chevy Impala, battling every kind of supernatural threat they encounter " +
                        "along the way.",
                    "10/13/2005",
                    "Drama, Mystery, Sci-Fi & Fantasy",
                    "45 minutes/ episode",
                    "81%",
                    "tvshow_supernatural"
                )
            )

            add(
                TvShowModel(
                    10,
                    "The Walking Dead",
                    "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic " +
                        "world dominated by flesh-eating zombies. He sets out to find his family and " +
                        "encounters many other survivors along the way.",
                    "10/31/2010",
                    "Action & Adventure, Drama, Sci-Fi & Fantasy",
                    "42 minutes / episode",
                    "79%",
                    "tvshow_the_walking_dead"
                )
            )
        }
        return tvShows
    }
}