package com.religada.bemobile.ui.main.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.religada.bemobile.R
import com.religada.bemobile.databinding.ItemTransactionBinding
import com.religada.bemobile.domain.Transaction
import com.religada.bemobile.ui.common.basicDiffUtilApp
import com.religada.bemobile.ui.common.inflate

class TransactionsAdapter(private val listener: (String) -> Unit) :
    ListAdapter<Transaction, TransactionsAdapter.ViewHolder>(basicDiffUtilApp { old, new -> old.id == new.id }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_transaction, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = getItem(position)
        holder.bind(transaction)
        holder.itemView.setOnClickListener { listener(transaction.sku) }
    }

    fun setUpdatedData(items: List<Transaction>?) {
        items?.let {
            this.submitList(items)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemTransactionBinding.bind(view)

        fun bind(transaction: Transaction) {
            binding.tvSku.text = transaction.sku
            binding.tvAmount.text = transaction.amount.toString()
            binding.tvCurrency.text = transaction.currency
        }
    }
}