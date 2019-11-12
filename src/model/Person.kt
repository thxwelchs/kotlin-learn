package model

/**
 * 테스트에 쓰일 "인간" 객체 모델
 */

data class Person (
    var name: String? = null,
    var age: Int? = null,
    var hobbies: MutableSet<String> = HashSet()
) {
    fun toEntity() = this
}