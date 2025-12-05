from typing import List, Tuple, Optional

def analyze_with_right_side(data: List[str]) -> List[Tuple[int, Optional[int]]]:
    """
    data: Tek elemanlı bir liste, içinde çok satırlı bir string.
    Fonksiyon:
      - Her satırı tek tek işler.
      - Soldan sağa: satır uzunluğunun 1 eksiği kadar alanda en büyük rakamı bulur.
      - Sağ tarafa: bu bulunan rakamın sağındaki alanda en büyük rakamı bulur.
    """
    # Çok satırlı tek stringi satırlara ayır
    lines = data[0].strip().splitlines()

    results: List[Tuple[int, Optional[int]]] = []

    for line in lines:
        s = line.strip()
        n = len(s)
        if n < 2:
            raise ValueError("Her satırda en az iki rakam olmalı.")

        digits = [int(c) for c in s]

        # 1) Soldan sağa tarama alanı: 0 .. n-2
        left_zone = digits[: n - 1]

        left_max = max(left_zone)
        left_index = left_zone.index(left_max)

        # 2) Sağ tarama alanı: left_index + 1 .. son
        right_zone = digits[left_index + 1 :]

        right_max = max(right_zone) if right_zone else None

        results.append((left_max, right_max))

    return results

# MAİN CODE ----------------------------------------

data = ["""
987654321111111 
811111111111119
234234234234278
818181911112111
"""]

print(analyze_with_right_side(data))
