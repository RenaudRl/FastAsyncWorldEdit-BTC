import io.papermc.paperweight.userdev.PaperweightUserDependenciesExtension

plugins {
    id("buildlogic.adapter")
}

// workaround LeafPile providing wrong snakeyaml version
configurations.named("testRuntimeClasspath") {
    exclude(group = "org.yaml", module = "snakeyaml")
}

dependencies {
    // https://artifactory.papermc.io/ui/native/universe/io/papermc/paper/dev-bundle/
    // BTC: pinned to the Paper build BTC-CORE is based on (paperRef f68a55a8 = 26.3 build 191), so the
    // compiler checks the adapter against our server. Move it whenever BTC-CORE moves its paperRef.
    the<PaperweightUserDependenciesExtension>().paperDevBundle("26.3.build.191-stable")
    testRuntimeOnly(libs.snakeyaml) {
        because("Keep adapter tests on Bukkit's SnakeYaml while excluding LeafPile's conflicting transitive version")
    }
}
