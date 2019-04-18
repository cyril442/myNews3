package com.example.mynews.NewYorkTimesApi

import com.example.mynews.Fragments.DataFromNyt

fun mapNytDataToDataFromNyt (nytWrapper: NytWrapper) : DataFromNyt {
    val dataFromNytFirst : ResultsData = nytWrapper.results.first()
     val dataFromNytSecond : Multimedia = nytWrapper.results.first().multimedia?.first() ?: nytWrapper.results.first().multimedia!!.first()



    return DataFromNyt(
        title = dataFromNytFirst.title,
        section = dataFromNytFirst.section,
        subsection = dataFromNytFirst.subsection,
        date = dataFromNytFirst.date,
        urlArticle = dataFromNytFirst.urlArticle,
        iconUrl = dataFromNytSecond.iconUrl,
        caption = dataFromNytSecond.caption

    )

}

fun mapNytDataToDataFromNytSearch (nytWrapperSearch: NytWrapperSearch) : DataFromNyt {

val dataFromNytSearchFirst : Docs = nytWrapperSearch.docs?.first()
    val dataFromNytSearchSecond : Images = nytWrapperSearch.docs.first().images?.first() ?: nytWrapperSearch.docs.first().images!!.first()

    return DataFromNyt(
        title = dataFromNytSearchFirst.title,
        section = dataFromNytSearchFirst.section,
        subsection = dataFromNytSearchFirst.subsection,
        date = dataFromNytSearchFirst.date,
        urlArticle = dataFromNytSearchFirst.urlArticle,
        iconUrl = dataFromNytSearchSecond.iconUrl,
        caption = dataFromNytSearchSecond.caption

    )
}