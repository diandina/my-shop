package com.funtl.my.shop.domain;

public class TbSeat {
    private Long id;

    private Long hallId;

    private Long movieId;

    private Long orderItemOrderId;

    private Integer col;

    private Integer row;

    public Long getOrderItemOrderId() {
        return orderItemOrderId;
    }

    public void setOrderItemOrderId(Long orderItemOrderId) {
        this.orderItemOrderId = orderItemOrderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }
}