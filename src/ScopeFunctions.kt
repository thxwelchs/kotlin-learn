import model.Person

/**
 * 범위 지정 함수에 대해 배워보기.
 */

fun main(args : Array<String>) {
    scopeFunctionsWith()
    scopeFunctionsAlso()
    scopeFunctionsApply()
    scopeFunctionsLet()
    scopeFunctionsRun()
}

val person = Person("이태훈", 29, mutableSetOf("TV보기", "프로그래밍", "낚시", "컴퓨터 게임"))


/**
 * with(param) { ... } 파라미터로 주체를 암시적으로 전달합니다.
 *
 * 용도: 인자의 멤버에 간단하게 접근하려고 할 때 사용합니다.
 *
 * 밑의 정의에서 receiver 가 수신 객체 , block 이 수신 객체 지정 람다가 되어집니다.
 *  inline fun <T, R> with(receiver: T, block: T.() -> R): R {
        return receiver.block()
    }
 *
 * @return 람다를 실행한 결과 값
 */
fun scopeFunctionsWith() {
    println("--- with scope ---")

    val withResult = with(person) {
        println("${this.name}의 나이는 ${this.age}살 입니다.")
        println("${name}의 나이는 ${age}살 입니다.") // <- 이렇게 this를 생략할 수도 있다. 하지만 소스코드의 가독성을 위해 권장하지는 않음.

        this // 반환 값
    }

    println(withResult) // with 함수의 결과 값 (위 에서 this를 반환했으므로 person 객체가 반환됩니다. 아무것도 반환하지 않았을 경우 Unit이 반환됨
}


/**
 * also { ... } 리시버(<T>)로 주체를 암시적으로 전달합니다.
 *
 * 용도: 객체의 사이드 이팩트를 확인하거나 체이닝, 수신 객체의 프로퍼티에 데이터를 할당하기 전에 해당 데이터의 유효성을 검사하는 등에 사용,
  객체의 프로퍼티 값을 변경 하지 않으면서 추가 작업 하는경우에 쓰입니다.
 *
 * inline fun <T> T.also(block: (T) -> Unit): T {
        block(this)
        return this
    }
 * @return 블록 내에 전달된 수신객체
 */
fun scopeFunctionsAlso() {
    println("--- also scope ---")

    person.toEntity()
        .also { println("나이는? ${it.age}") } // 특정 메소드에 체이닝을 해주는 예
        .takeIf { it.age ==29 } // takeIf 라는 스코프 함수인데. 판단결과(Boolean) 에 따라서 null 혹은 자기자신(this)를 return 해줍니다.
    //  .something()
}

/**
 * apply { ... } 리시버(<T>)로 주체를 암시적으로 전달합니다.
 *
 * 용도: 수신 객체 람다 내부에서 수신 객체의 함수를 사용하지 않고 수신 객체 자신을 다시 반환 하려는 경우에 apply 를 사용합니다.
수신 객체 의 프로퍼티 만을 사용하는 대표적인 경우가 객체의 초기화 이며, 이곳에 apply 를 사용합니다.

 * inline fun <T> T.apply(block: T.() -> Unit): T {
        block()
        return this
    }
 * @return 전달 받은 수신 객체
 */
fun scopeFunctionsApply() {
    println("--- apply scope ---")

    val resultApply = Person().apply {
        name = "홍길동"
        age = 29
    }

    println(resultApply)
}

/**
 * let { ... } 리시버(<T>)로 주체를 암시적으로 전달합니다.
 *
 * 용도: 지정된 값이 null 이 아닌 경우에 코드를 실행해야 하는 경우.
    Nullable 객체를 다른 Nullable 객체로 변환하는 경우.
    단일 지역 변수의 범위를 제한 하는 경우.
 *
 * @return 블록의 수행 결과
 */
fun scopeFunctionsLet() {
    println("--- let scope ---")

    val notNullPerson = person

    notNullPerson?.let {
        println(it.name) // null이 아닐 때만 실행
    }

    null?.let {
        println(" this is null ") // 실행 안됨
    }

    var nullablePerson: Person? = null
    person.let {
        nullablePerson = it
    }

}


/**
 * run { ... } 리시버(<T>)로 주체를 암시적으로 전달합니다.
 *
 * 용도: 어떤 값을 계산할 필요가 있거나 여러개의 지역 변수의 범위를 제한할 때 사용합니다.
 *
 * @return 블록의 수행 결과
 */
fun scopeFunctionsRun() {
    println("--- run scope ---")

    person.run {
        hobbies.add("음악듣기")
        hobbies.add("유튜브 보기")

        println("${name}의 취미는 몇개? ${hobbies.size}개")
    }
}
