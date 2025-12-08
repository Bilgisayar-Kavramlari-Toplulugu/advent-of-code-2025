# FORMATLAMA ---------------------------------------
def wrap_with_full_border(data: str):
    # Satırları temizle
    lines = [line.strip() for line in data.strip().split("\n") if line.strip()]

    # Her satırı yatay olarak . ile çerçevele
    wrapped = [f".{line}." for line in lines]

    # Kaç satır varsa, o kadar "." içeren bir üst/alt sınır satırı oluştur
    row_length = len(wrapped[0])              # Örneğin ".@@@.@.@.@@." gibi
    full_dot_row = "." * row_length           # Bu kadar uzun bir "......." satırı

    # Listenin başına ve sonuna ekle
    wrapped.insert(0, full_dot_row)
    wrapped.append(full_dot_row)

    return wrapped
    
# --------------------------------------------------
# ARAMA --------------------------------------------
def count_all_similar_neighbors(data: list, match_char: str):
    # Tüm data'yı ilk elemana string formatında ekle
    result = []

    for row in range(1, len(data) - 1):
        for col in range(1, len(data[row]) - 1):
            merkez = data[row][col]
            if merkez != match_char:
                continue

            benzer = 0

            offsets = [
                (-1, -1), (-1, 0), (-1, 1),
                (0, -1),           (0, 1),
                (1, -1),  (1, 0),  (1, 1)
            ]

            for dr, dc in offsets:
                r = row + dr
                c = col + dc
                if data[r][c] == merkez:
                    benzer += 1

            if benzer < 4:
                result.append(((row, col), benzer))

    return result

# --------------------------------------------------
# 1. SORUNUN CEVABI---------------------------------
def count_portable_rolls(data: list, match_char: str):
    results = 0

    sonuclar = count_all_similar_neighbors(data,"@")
    
    for konum, sayi in sonuclar:
        results += 1
        #print(f"{konum} → {sayi}")
        
    print(f"1. sorunun cevabı → {results}")
    
# --------------------------------------------------
# 2. SORUNUN CEVABI---------------------------------
def update_data_list(data: list, match_char: str):
    number = 0
    
    sonuclar = count_all_similar_neighbors(data, match_char)

    for (row, col), sayi in sonuclar:
        number += 1

        row_list = list(data[row])
        row_list[col] = "."
        data[row] = "".join(row_list)

    return (data, number)
    
def count_all_portable_rolls(data: list, match_char: str):
    results = 0

    while True:
        son_data, number = update_data_list(data, match_char)

        if number == 0:
            break

        results += number

    print(f"2. sorunun cevabı → {results}")

# --------------------------------------------------
# MAİN CODE ----------------------------------------
data = """

"""

formatted_data = wrap_with_full_border(data)
count_portable_rolls(formatted_data,"@")
count_all_portable_rolls(formatted_data,"@")
