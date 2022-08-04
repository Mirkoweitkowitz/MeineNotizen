package com.example.meinenotizen

import com.example.meinenotizen.adapter.NotizenAdapter

interface OnDragStartListener {

    fun onDragStart(holder: NotizenAdapter.ItemViewHolder?)
}