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
    // BTC: pinned to the Paper build BTC-CORE is based on (paperRef 0fd1cf72 = 26.3 build 18), which
    // predates the re-applied feature patches. Drop this pin once BTC-CORE moves to a newer base.
    the<PaperweightUserDependenciesExtension>().paperDevBundle("26.3.build.18-alpha")
    testRuntimeOnly(libs.snakeyaml) {
        because("Keep adapter tests on Bukkit's SnakeYaml while excluding LeafPile's conflicting transitive version")
    }
}
