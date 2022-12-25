package com.example.filmpopuler_taufan.util

import com.example.filmpopuler_taufan.data.remote.model.configuration.ConfigurationResponse


/*
file ini berisi kumpulan dari konfigurasi data
 */
object ConfigurationStore {

    private lateinit var configurationData: ConfigurationResponse

    fun setConfigurationData(configurationData: ConfigurationResponse){
        this.configurationData=configurationData
    }

    fun getConfigurationData(): ConfigurationResponse {
        return configurationData
    }


}