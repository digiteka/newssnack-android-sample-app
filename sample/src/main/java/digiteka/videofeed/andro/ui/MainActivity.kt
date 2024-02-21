package digiteka.videofeed.andro.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import digiteka.videofeed.andro.databinding.MainActivityBinding
import digiteka.videofeed.andro.databinding.SampleItemBinding
import digiteka.videofeed.andro.logic.entities.SampleEntity
import digiteka.videofeed.andro.logic.entities.SampleProvider

class MainActivity : AppCompatActivity() {

	private lateinit var binding: MainActivityBinding

	private val mainAdapter: MainAdapter by lazy { MainAdapter(::onSampleClicked) }
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = MainActivityBinding.inflate(layoutInflater)
		setContentView(binding.root)
		binding.mainActivityRecyclerView.adapter = mainAdapter
		setSamples()
	}

	private fun setSamples() {
		mainAdapter.setSamples(SampleProvider.getSamples())
	}

	private fun onSampleClicked(sample: SampleEntity<*>) {
		val intent = Intent(this, sample.activityClass)
		startActivity(intent)
	}

	private class MainAdapter(
		private val onSampleClicked: (SampleEntity<*>) -> Unit
	) : RecyclerView.Adapter<MainAdapter.SampleItem>() {
		companion object {
			private const val TYPE_SAMPLE = 0
		}

		private val samples = mutableListOf<SampleEntity<*>>()

		@SuppressLint("NotifyDataSetChanged")
		fun setSamples(samples: List<SampleEntity<*>>) {
			this.samples.clear()
			this.samples.addAll(samples)
			notifyDataSetChanged()
		}

		override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleItem {
			val layoutInflater = LayoutInflater.from(parent.context)
			return when (viewType) {
				TYPE_SAMPLE -> SampleItem(SampleItemBinding.inflate(layoutInflater, parent, false), onSampleClicked)
				else -> throw RuntimeException("Unknown viewType $viewType")
			}
		}

		override fun getItemCount(): Int = samples.size

		override fun onBindViewHolder(holder: SampleItem, position: Int) = samples.getOrNull(position)?.let { holder.bind(it) } ?: Unit

		override fun getItemViewType(position: Int): Int = TYPE_SAMPLE
		private class SampleItem(
			private val binding: SampleItemBinding,
			val onSampleClicked: (SampleEntity<*>) -> Unit
		) : ViewHolder(binding.root) {

			fun bind(sample: SampleEntity<*>) {
				val context = binding.root.context
				// label binding
				binding.sampleItemLabel.text = sample.label
				binding.sampleItemLabel.typeface = if (sample.font != null) ResourcesCompat.getFont(context, sample.font) else null

				//Button binding
				binding.sampleItemButton.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(context, sample.secondaryColor)))
				binding.sampleItemButton.setBackgroundColor(ContextCompat.getColor(context, sample.primaryColor))
				binding.sampleItemButton.setOnClickListener { onSampleClicked(sample) }
			}
		}
	}
}