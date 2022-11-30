//package com.religada.bemobile.ui.main.adapter
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.religada.bemobile.R
//import com.religada.bemobile.databinding.ItemTransactionBinding
//import com.religada.bemobile.domain.Transaction
// TODO BORRAR
//class RecyclerViewAdapterTransactions(
//        private val items: MutableList<Transaction>,
//        private val onClickItem: (sku: String) -> Unit,
//    ) : RecyclerView.Adapter<RecyclerViewAdapterTransactions.ListViewHolder>() {
//
//        override fun onCreateViewHolder(
//            parent: ViewGroup,
//            viewType: Int
//        ): ListViewHolder {
//            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)
//
//            return ListViewHolder(view, onClickItem)
//        }
//
//        override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//            holder.bind(items[position])
//        }
//
//        override fun getItemCount(): Int {
//            return items.size
//        }
//
//        fun setUpdatedData(items: List<Transaction>) {
//            val currentPosition = this.items.size
//            this.items.addAll(items)
//            notifyItemRangeInserted(currentPosition, items.size)
//        }
//
//        class ListViewHolder(
//            val view: View,
//            private val onClickItem: (sku: String) -> Unit,
//        ) : RecyclerView.ViewHolder(view) {
//
//            private val binding = ItemTransactionBinding.bind(view)
//
//            fun bind(transaction: Transaction) {
//                try {
//                    binding.boxItem.setOnClickListener {
//                        onClickItem(transaction.sku)
//                    }
//                    binding.tvSku.text = transaction.sku
//                    binding.tvAmount.text= transaction.amount.toString()
//                    binding.tvCurrency.text = transaction.currency
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//            }
//        }
//    }
