package com.example.horoscopo.data

import com.example.horoscopo.R

class HoroscopeProvider {
    companion object {
        private val horoscopeList: List<Horoscope> = listOf(
            Horoscope(
                "aries",
                R.string.horoscopo_aries,
                R.string.horoscope_date_aries,
                R.drawable.aries_icon,
                R.string.horoscope_message_aries
            ),
            Horoscope(
                "tauro",
                R.string.horoscopo_tauro,
                R.string.horoscope_date_taurus,
                R.drawable.taurus_icon,
                R.string.horoscope_message_tauro
            ),
            Horoscope(
                "geminis",
                R.string.horoscopo_geminis,
                R.string.horoscope_date_gemini,
                R.drawable.gemini_icon,
                R.string.horoscope_message_geminis
            ),
            Horoscope(
                "cancer",
                R.string.horoscopo_cancer,
                R.string.horoscope_date_cancer,
                R.drawable.cancer_icon,
                R.string.horoscope_message_cancer
            ),
            Horoscope(
                "leo",
                R.string.horoscopo_leo,
                R.string.horoscope_date_leo,
                R.drawable.leo_icon,
                R.string.horoscope_message_leo
            ),
            Horoscope(
                "virgo",
                R.string.horoscopo_virgo,
                R.string.horoscope_date_virgo,
                R.drawable.virgo_icon,
                R.string.horoscope_message_virgo
            ),
            Horoscope(
                "libra",
                R.string.horoscopo_libra,
                R.string.horoscope_date_libra,
                R.drawable.libra_icon,
                R.string.horoscope_message_libra
            ),
            Horoscope(
                "escorpio",
                R.string.horoscopo_escorpio,
                R.string.horoscope_date_scorpio,
                R.drawable.scorpio_icon,
                R.string.horoscope_message_escorpio
            ),
            Horoscope(
                "sagitario",
                R.string.horoscopo_sagitario,
                R.string.horoscope_date_sagittarius,
                R.drawable.sagittarius_icon,
                R.string.horoscope_message_sagitario
            ),
            Horoscope(
                "capricornio",
                R.string.horoscopo_capricornio,
                R.string.horoscope_date_capricorn,
                R.drawable.capricorn_icon,
                R.string.horoscope_message_capricornio
            ),
            Horoscope(
                "acuario",
                R.string.horoscopo_acuario,
                R.string.horoscope_date_aquarius,
                R.drawable.aquarius_icon,
                R.string.horoscope_message_acuario
            ),
            Horoscope(
                "piscis",
                R.string.horoscopo_piscis,
                R.string.horoscope_date_pisces,
                R.drawable.pisces_icon,
                R.string.horoscope_message_piscis
            )
        )

        fun findAll(): List<Horoscope> {
            return horoscopeList
        }

        fun findById(id: String): Horoscope {
            return horoscopeList.find { it.id == id }!!
        }

        fun searchHoroscopes(query: String): List<Horoscope> {
            return horoscopeList.filter { it.id.contains(query, ignoreCase = true) }
        }
    }
}