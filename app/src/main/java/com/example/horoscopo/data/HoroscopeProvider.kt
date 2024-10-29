package com.example.horoscopo.data

import com.example.horoscopo.R

class HoroscopeProvider {
    val horoscopeList: List<Horoscope> = listOf(
        Horoscope("aries", R.string.horoscopo_aries, R.string.horoscope_date_aries, R.drawable.aries_icon),
        Horoscope("tauro", R.string.horoscopo_tauro, R.string.horoscope_date_taurus, R.drawable.taurus_icon),
        Horoscope("geminis", R.string.horoscopo_geminis, R.string.horoscope_date_gemini, R.drawable.gemini_icon),
        Horoscope("cancer", R.string.horoscopo_cancer, R.string.horoscope_date_cancer, R.drawable.cancer_icon),
        Horoscope("leo", R.string.horoscopo_leo, R.string.horoscope_date_leo, R.drawable.leo_icon),
        Horoscope("virgo", R.string.horoscopo_virgo, R.string.horoscope_date_virgo, R.drawable.virgo_icon),
        Horoscope("libra", R.string.horoscopo_libra, R.string.horoscope_date_libra, R.drawable.libra_icon),
        Horoscope("escorpio", R.string.horoscopo_escorpio, R.string.horoscope_date_scorpio, R.drawable.scorpio_icon),
        Horoscope("sagitario", R.string.horoscopo_sagitario, R.string.horoscope_date_sagittarius, R.drawable.sagittarius_icon),
        Horoscope("capricornio", R.string.horoscopo_capricornio, R.string.horoscope_date_capricorn, R.drawable.capricorn_icon),
        Horoscope("acuario", R.string.horoscopo_acuario, R.string.horoscope_date_aquarius, R.drawable.aquarius_icon),
        Horoscope("piscis", R.string.horoscopo_piscis, R.string.horoscope_date_pisces, R.drawable.pisces_icon)
    )

    fun findAll(): List<Horoscope>{
            return horoscopeList
    }

    fun findById(id: String): Horoscope {
        return horoscopeList.find { it.id == id }!!
    }
}