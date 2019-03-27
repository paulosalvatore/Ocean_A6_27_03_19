package br.com.paulosalvatore.ocean_a6_27_03_19

data class GitHubRepositoriesResult(
        val items: List<Repository>
)

data class Repository(
        val id: Long?,
        val name: String?,
        val full_name: String?,
        val description: String?,
        val owner: Owner
)

data class Owner(
        val id: Long?,
        val login: String?,
        val avatar_url: String?
)
