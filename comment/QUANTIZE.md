8.9 QUANTIZE (중)
===
1. 문제 재구성
2. 문제 해결 계획
3. 계획 검증
4. 회고


1. 무식하게 풀기
    1. 문제해결 계획
        1. 수열의 최대값과 최소값을 구한다.
        2. 최대값과 최소값 사이의 수 중에서 s개의 숫자를 고른다.(중복해서 고르는 것이 가능하다고 하자. 조합도 가능하겠지만)
        3. 각 경우에서 제곱합을 구한다.
        4. 3에서 구한값들 중에서 최소값을 구한다.
    2. 계획 검증
        1. 이렇게 하면 문제가 풀릴 것이라는 것은 분명하다. 빠진것 없이 완전 탐색을 했기 때문이다.
        2. 시간 복잡도 계산
            1. 수열의 수는 1000이하의 자연수이다. 따라서 Domain인 d에 대한 최대값 1000이라고 하자. 수열의 길이 n은 100 , 숫자의 수는 s는 10
            2. O(d^s * n) ~ 1000^10 * 100 = 10^32이므로 우리의 기준인 2^32보다 5^32배 큰 수이다. 따라서 이 방법을 사용할 수 없다.
             
2. 풀이
    1. 문제해결 계획
        1. 수열의 최대값과 최소값을 구한다.
        2. 작은 숫자부터 하나씩 고른다. 이렇게 하면 중복해서 고르는 경우를 배제할 수 있다.
 
회고

1. 일단 맨 처음에 어떻게 문제를 줄일지 아이디어를 얻지 못하였다. 풀이를 보기 전에 이걸 구간 별로 잘라서 생각하면 되겠다는 희미한 실마리는 가지고 있었는데 풀이에서와 같이 해당 구역의 minError를 구하는 방식으로 할 것을 생각 못했다.
2. 특히 quantize(from, size)와 같은 구조를 생각 못했는데 다음부터는 주어진 문제를 머리 속에서 잘 변형해보도록 노력해봐야겠다.
3. 이번 문제는 특히 디버깅에 애를 먹었다. 맨 마지막에 정확히 뭘 잘못했는지는 모르겠지만, minError를 from, length에서 from, to를 인자로 받도록 하고 이에 맞게 수정한 것만으로 답이 맞게 되었다. 정확한 이유는 잘 모르겠지만, 앞으로 특정 배열의 범위에 접근하는 것은 무조건 from to 구조를 갖도록 해야겠다. 위에 있는 인자들이 하나는 특정 지점을 말하고 하나는 길이를 말한다면 두 인자의 맥락이 달라지기 때문에 오류의 가능성이 커지기 때문이다.
4. 자잘한 실수가 무지 많았다. 함수 하나 하나에서 실수를 무지 많이 했다. base case설정을 제대로 안해놓았거나, 다른 변수를 넣는 경우 등이 있었다. 조금더 변수 이름에 신경 쓰고 특히 i, j를 잘못쓰지 않도록 해야겠다. 이제부터 차라리 m, n, o나 a, b, c를 써서 확실히 분간이 되게 만들던가 해야겠다.
5. 정확한 이유는 모르겠지만 ret = Inter.MAX_VALUE를 이용하니까 이상한 값이 나오는 문제가 있었다. 987654321을 대용으로 쓰는게 좋겠다. 아마도 어딘가에서 +가 되면 -값이 되는 오버플로우 문제때문에 그런것 같다.
6. 3번 문제는 해결을 했다. 이것도 정확한 이유는 모르겠는데 size별로 나눠서 quantize하는게 문제가 되었던 것 같다. 조금 더 고민해볼 필요가 있겠다.