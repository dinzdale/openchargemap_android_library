package com.gmjacobs.productions.openchargemap.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.gmjacobs.productions.openchargemap.R
import com.gmjacobs.productions.openchargemap.model.poi.PoiItem

/*
Icons and most of the logic here borrowed from https://github.com/openchargemap/ocm-app/tree/master/src/assets/images/icons/connectors
 */
class MapMarkers(val context: Context) {
    companion object {
        //
        private lateinit var resources: Resources
        val level0_nonoperationl: Bitmap by lazy<Bitmap> {
            BitmapFactory.decodeResource(
                resources,
                R.drawable.level0_nonoperational_icon
            )
        }
        val level0_operational: Bitmap by lazy<Bitmap> {
            BitmapFactory.decodeResource(
                resources,
                R.drawable.level0_operational_icon
            )
        }
        val level0_private: Bitmap by lazy<Bitmap> {
            BitmapFactory.decodeResource(
                resources,
                R.drawable.level0_private_icon
            )
        }

        //
        val level1_nonoperationl: Bitmap by lazy<Bitmap> {
            BitmapFactory.decodeResource(
                resources,
                R.drawable.level1_nonoperational_icon
            )
        }
        val level1_operational: Bitmap by lazy<Bitmap> {
            BitmapFactory.decodeResource(
                resources,
                R.drawable.level1_operational_icon
            )
        }
        val level1_private: Bitmap by lazy<Bitmap> {
            BitmapFactory.decodeResource(
                resources,
                R.drawable.level1_private_icon
            )
        }

        //
        val level2_nonoperationl: Bitmap by lazy<Bitmap> {
            BitmapFactory.decodeResource(
                resources,
                R.drawable.level2_nonoperational_icon
            )
        }
        val level2_operational: Bitmap by lazy<Bitmap> {
            BitmapFactory.decodeResource(
                resources,
                R.drawable.level2_operational_icon
            )
        }
        val level2_private: Bitmap by lazy<Bitmap> {
            BitmapFactory.decodeResource(
                resources,
                R.drawable.level2_private_icon
            )
        }

        //
        val level3_nonoperationl: Bitmap by lazy<Bitmap> {
            BitmapFactory.decodeResource(
                resources,
                R.drawable.level3_nonoperational_icon
            )
        }
        val level3_operational: Bitmap by lazy<Bitmap> {
            BitmapFactory.decodeResource(
                resources,
                R.drawable.level3_operational_icon
            )
        }
        val level3_private: Bitmap by lazy<Bitmap> {
            BitmapFactory.decodeResource(
                resources,
                R.drawable.level3_private_icon
            )
        }

    }

    init {
        resources = context.resources
    }


    fun getIconForPOI(poi: PoiItem): Bitmap {
        val poiLevel = getMaxLevelOfPoi(poi)
        var status = "operational"

        poi.usageType?.title?.let {
            if (it.contains("private", true)) {
                status = "private"
            }
        } ?: let {
            poi.statusType?.isOperational?.let {
                if (it.not()) {
                    status = "nonoperational"
                }
            } ?: let {
                status = "operational"
            }
        }

        return when (poiLevel) {
            1 -> when (status) {
                "private" -> level1_private
                "nonoperational" -> level1_nonoperationl
                else -> level1_operational
            }
            2 -> when (status) {
                "private" -> level2_private
                "nonoperational" -> level2_nonoperationl
                else -> level2_operational
            }
            3 -> when (status) {
                "private" -> level3_private
                "nonoperational" -> level3_nonoperationl
                else -> level3_operational
            }
            else -> when (status) {
                "private" -> level0_private
                "nonoperational" -> level0_nonoperationl
                else -> level0_operational
            }

        }
    }

    fun getMaxLevelOfPoi(poi: PoiItem): Int {
        var level = 0
        poi.connections?.forEach {
            it.level?.let {
                if (it.iD > level) {
                    level = it.iD
                }
            }
        }
        return when {
            level == 4 -> 2
            level > 4 -> 3
            else -> level
        }

    }

}