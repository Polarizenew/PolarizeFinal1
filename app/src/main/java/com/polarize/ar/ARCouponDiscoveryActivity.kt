package com.polarize.ar

import android.os.Bundle
import com.google.ar.core.Anchor
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import androidx.appcompat.app.AppCompatActivity
import com.polarize.R

class ARCouponDiscoveryActivity : AppCompatActivity() {

    private lateinit var arFragment: ArFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar_coupon_discovery)

        arFragment = supportFragmentManager.findFragmentById(R.id.ar_fragment) as ArFragment

        arFragment.setOnTapArPlaneListener { hitResult, _, _ ->
            val anchor: Anchor = hitResult.createAnchor()
            ModelRenderable.builder()
                .setSource(this, R.raw.coupon_model)  // Replace with actual GLB model in assets
                .build()
                .thenAccept { renderable ->
                    val anchorNode = AnchorNode(anchor)
                    anchorNode.setParent(arFragment.arSceneView.scene)
                    val node = TransformableNode(arFragment.transformationSystem)
                    node.setParent(anchorNode)
                    node.renderable = renderable
                    node.select()
                }
        }
    }
}
