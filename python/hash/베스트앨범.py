def solution(genres, plays):
    answer = []
    total_plays = {}
    song_plays = {}
    for i, key in enumerate(genres):
        total_plays[key] = total_plays.get(key, 0) + plays[i]
        song_plays[key] = song_plays.get(key, [])
        song_plays[key].append((plays[i], i))

    sorted_genres = sorted(total_plays, key = lambda x : -total_plays[x])
    for key in sorted_genres:
        song_list = song_plays[key]
        song_list.sort(key = lambda x : (-x[0], x[1]))
        for i in range(len(song_list)):
            answer.append(song_list[i][1])
            if i == 1:
                break
    return answer
